package com.ljw.spring.source.s1.di;


import com.ljw.spring.source.s1.annotation.AutowiredPlus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


//@Component
public class AutowiredPlusAnnotationMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor, PriorityOrdered,  BeanFactoryAware {

    protected final Log logger = LogFactory.getLog(getClass());

    private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(4);

    private String requiredParameterName = "required";

    private boolean requiredParameterValue = true;

    private int order = Ordered.LOWEST_PRECEDENCE - 300;

    @Nullable
    private ConfigurableListableBeanFactory beanFactory;

    private final Set<String> lookupMethodsChecked = Collections.newSetFromMap(new ConcurrentHashMap<>(256));

    private final Map<Class<?>, Constructor<?>[]> candidateConstructorsCache = new ConcurrentHashMap<>(256);

    private final Map<String, InjectionMetadata> injectionMetadataCache = new ConcurrentHashMap<>(256);


    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
//        AutowiredAnnotationBeanPostProcessor
        System.out.println("--new DI ----AutowiredPlusAnnotationMergedBeanDefinitionPostProcessor");
        /**
         * 核心代码
         */
        InjectionMetadata metadata = findAutowiringPlusMetadata(beanName, beanType, null);
        metadata.checkConfigMembers(beanDefinition);
    }


    public AutowiredPlusAnnotationMergedBeanDefinitionPostProcessor() {

        /**
         * 搜索处理Autowired注解
         */
        this.autowiredAnnotationTypes.add(AutowiredPlus.class);

    }


    /**
     * Set the 'autowired' annotation type, to be used on constructors, fields,
     * setter methods, and arbitrary config methods.
     * <p>The default autowired annotation types are the Spring-provided
     * {@link Autowired @Autowired} and {@link Value @Value} annotations as well
     * as JSR-330's {@link javax.inject.Inject @Inject} annotation, if available.
     * <p>This setter property exists so that developers can provide their own
     * (non-Spring-specific) annotation type to indicate that a member is supposed
     * to be autowired.
     */
    public void setAutowiredAnnotationType(Class<? extends Annotation> autowiredAnnotationType) {
        Assert.notNull(autowiredAnnotationType, "'autowiredAnnotationType' must not be null");
        this.autowiredAnnotationTypes.clear();
        this.autowiredAnnotationTypes.add(autowiredAnnotationType);
    }

    /**
     * Set the 'autowired' annotation types, to be used on constructors, fields,
     * setter methods, and arbitrary config methods.
     * <p>The default autowired annotation types are the Spring-provided
     * {@link Autowired @Autowired} and {@link Value @Value} annotations as well
     * as JSR-330's {@link javax.inject.Inject @Inject} annotation, if available.
     * <p>This setter property exists so that developers can provide their own
     * (non-Spring-specific) annotation types to indicate that a member is supposed
     * to be autowired.
     */
    public void setAutowiredAnnotationTypes(Set<Class<? extends Annotation>> autowiredAnnotationTypes) {
        Assert.notEmpty(autowiredAnnotationTypes, "'autowiredAnnotationTypes' must not be empty");
        this.autowiredAnnotationTypes.clear();
        this.autowiredAnnotationTypes.addAll(autowiredAnnotationTypes);
    }

    /**
     * Set the name of an attribute of the annotation that specifies whether it is required.
     * @see #setRequiredParameterValue(boolean)
     */
    public void setRequiredParameterName(String requiredParameterName) {
        this.requiredParameterName = requiredParameterName;
    }

    /**
     * Set the boolean value that marks a dependency as required.
     * <p>For example if using 'required=true' (the default), this value should be
     * {@code true}; but if using 'optional=false', this value should be {@code false}.
     * @see #setRequiredParameterName(String)
     */
    public void setRequiredParameterValue(boolean requiredParameterValue) {
        this.requiredParameterValue = requiredParameterValue;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
            throw new IllegalArgumentException(
                    "AutowiredAnnotationBeanPostProcessor requires a ConfigurableListableBeanFactory: " + beanFactory);
        }
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }


    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) {

        /**
         * 此时，需要注入，获取注解对象
         * 还是这个方法，和收集注解的对象的过程一样
         * 在这个时候，就是直接从缓存中拿了
         */
        InjectionMetadata metadata = findAutowiringPlusMetadata(beanName, bean.getClass(), pvs);


        try {

            /**
             * DI-核心代码-3
             * 依赖注入
             *
             * TODO:这里要写AutowiredPlus的inject方法
             */
            metadata.inject(bean, beanName, pvs);

        }
        catch (BeanCreationException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new BeanCreationException(beanName, "Injection of autowired dependencies failed", ex);
        }
        return pvs;
    }

    private InjectionMetadata findAutowiringPlusMetadata(String beanName, Class<?> clazz, @Nullable PropertyValues pvs) {
        // Fall back to class name as cache key, for backwards compatibility with custom callers.
        String cacheKey = (StringUtils.hasLength(beanName) ? beanName : clazz.getName());
        // Quick check on the concurrent map first, with minimal locking.

        /**
         * 首先从缓存中拿到，对应的有注解的属性和方法
         * metadata就是包装有注解的对象和方法
         */
        InjectionMetadata metadata = this.injectionMetadataCache.get(cacheKey);
        if (InjectionMetadata.needsRefresh(metadata, clazz)) {
            synchronized (this.injectionMetadataCache) {
                metadata = this.injectionMetadataCache.get(cacheKey);
                if (InjectionMetadata.needsRefresh(metadata, clazz)) {
                    if (metadata != null) {
                        metadata.clear(pvs);
                    }

                    /**
                     * 核心代码
                     * 把对应的类赋值初始化进去
                     */
                    metadata = buildAutowiringMetadata(clazz);


                    this.injectionMetadataCache.put(cacheKey, metadata);
                }
            }
        }
        return metadata;
    }

    private InjectionMetadata buildAutowiringMetadata(final Class<?> clazz) {
        if (!AnnotationUtils.isCandidateClass(clazz, this.autowiredAnnotationTypes)) {
            return InjectionMetadata.EMPTY;
        }

        List<InjectionMetadata.InjectedElement> elements = new ArrayList<>();
        Class<?> targetClass = clazz;





        do {

            //容器内部对象都继承了InjectedElement
            final List<InjectionMetadata.InjectedElement> currElements = new ArrayList<>();


            /**
             * 字段上的Autowired注解
             * 搜集类上所有field属性，是否有Autowired注解
             */
            ReflectionUtils.doWithLocalFields(targetClass, field -> {
                MergedAnnotation<?> ann = findAutowiredAnnotation(field);
                if (ann != null) {
                    if (Modifier.isStatic(field.getModifiers())) {
                        if (logger.isInfoEnabled()) {
                            logger.info("Autowired annotation is not supported on static fields: " + field);
                        }
                        return;
                    }

                    /**
                     * 获取AutowiredPlus的注解信息
                     */
                    AnnotationAttributes annotationAttributes = getAnnotationAttributes(ann);

                    String value = (String) annotationAttributes.get("value");
                    String[] impls = (String[]) annotationAttributes.get("impls");
                    boolean required = (boolean) annotationAttributes.get(requiredParameterName);

                    //包装成一个AutowiredFieldElement对象，继承了InjectedElement，并放入容器
                    currElements.add(new AutowiredPlusAnnotationMergedBeanDefinitionPostProcessor.AutowiredPlusFieldElement(field, required, value, impls));
                }
            });

            elements.addAll(0, currElements);
            targetClass = targetClass.getSuperclass();
        }
        while (targetClass != null && targetClass != Object.class);

        //把类和所有注解的包装再次包装成一个对象返回
        return InjectionMetadata.forElements(elements, clazz);
    }

    @Nullable
    private MergedAnnotation<?> findAutowiredAnnotation(AccessibleObject ao) {
        MergedAnnotations annotations = MergedAnnotations.from(ao);
        for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
            MergedAnnotation<?> annotation = annotations.get(type);
            /**
             * 判断是否有目标注解被搜索到
             * 注解包含：@Autowired
             */
            if (annotation.isPresent()) {
                return annotation;
            }
        }
        return null;
    }

    /**
     * 获取AutowiredPlus的注解信息
     */
    @SuppressWarnings({"deprecation", "cast"})
    protected AnnotationAttributes getAnnotationAttributes(MergedAnnotation<?> ann) {
        // The following (AnnotationAttributes) cast is required on JDK 9+.
        return (AnnotationAttributes)
                ann.asMap(mergedAnnotation -> new AnnotationAttributes(mergedAnnotation.getType()));
    }



    /**
     * Obtain all beans of the given type as autowire candidates.
     * @param type the type of the bean
     * @return the target beans, or an empty Collection if no bean of this type is found
     * @throws BeansException if bean retrieval failed
     */
    protected <T> Map<String, T> findAutowireCandidates(Class<T> type) throws BeansException {
        if (this.beanFactory == null) {
            throw new IllegalStateException("No BeanFactory configured - " +
                    "override the getBeanOfType method or specify the 'beanFactory' property");
        }
        return BeanFactoryUtils.beansOfTypeIncludingAncestors(this.beanFactory, type);
    }



    /**
     * Register the specified bean as dependent on the autowired beans.
     */
    private void registerDependentBeans(@Nullable String beanName, Set<String> autowiredBeanNames) {
        if (beanName != null) {
            for (String autowiredBeanName : autowiredBeanNames) {
                if (this.beanFactory != null && this.beanFactory.containsBean(autowiredBeanName)) {
                    this.beanFactory.registerDependentBean(autowiredBeanName, beanName);
                }
                if (logger.isTraceEnabled()) {
                    logger.trace("Autowiring by type from bean name '" + beanName +
                            "' to bean named '" + autowiredBeanName + "'");
                }
            }
        }
    }



    /**
     * Resolve the specified cached method argument or field value.
     */
    @Nullable
    private Object resolvedCachedArgument(@Nullable String beanName, @Nullable Object cachedArgument) {
        if (cachedArgument instanceof DependencyDescriptor) {
            DependencyDescriptor descriptor = (DependencyDescriptor) cachedArgument;
            Assert.state(this.beanFactory != null, "No BeanFactory available");
            return this.beanFactory.resolveDependency(descriptor, beanName, null, null);
        }
        else {
            return cachedArgument;
        }
    }

    /**
     * Class representing injection information about an annotated field.
     */
    private class AutowiredPlusFieldElement extends InjectionMetadata.InjectedElement {

        private final boolean required;

        private final String value;
        private final String[] impls;

        private volatile boolean cached = false;

        @Nullable
        private volatile Object cachedFieldValue;

        public AutowiredPlusFieldElement(Field field, boolean required, String value, String[] impls) {
            super(field, null);
            this.required = required;
            this.value = value;
            this.impls = impls;
        }


        /**
         * 这里重写
         *
         * @param bean
         * @param beanName
         * @param pvs
         * @throws Throwable
         */
        @Override
        protected void inject(Object bean, @Nullable String beanName, @Nullable PropertyValues pvs) throws Throwable {

            /**
             * DI-核心代码-5
             *
             * 此时为字段DI注入
             */

            Field field = (Field) this.member;
            Object value;
            if (this.cached) {
                value = resolvedCachedArgument(beanName, this.cachedFieldValue);
            }
            else {
                DependencyDescriptor desc = new DependencyDescriptor(field, this.required);
                desc.setContainingClass(bean.getClass());
                Set<String> autowiredBeanNames = new LinkedHashSet<>(1);
                Assert.state(beanFactory != null, "No BeanFactory available");
                TypeConverter typeConverter = beanFactory.getTypeConverter();

                try {
                    /**
                     * 引用类型的成员
                     *
                     * 这个是成员变量触发自动装配
                     * 依赖注入：@Autowired在这里会触发自动装配getBean,
                     * 而@Value会触发${}字段的配置解析
                     *
                     */
                    value = beanFactory.resolveDependency(desc, beanName, autowiredBeanNames, typeConverter);
                }

                catch (BeansException ex) {
                    throw new UnsatisfiedDependencyException(null, beanName, new InjectionPoint(field), ex);
                }
                synchronized (this) {
                    if (!this.cached) {
                        if (value != null || this.required) {
                            this.cachedFieldValue = desc;
                            registerDependentBeans(beanName, autowiredBeanNames);
                            if (autowiredBeanNames.size() == 1) {
                                String autowiredBeanName = autowiredBeanNames.iterator().next();
                                if (beanFactory.containsBean(autowiredBeanName) &&
                                        beanFactory.isTypeMatch(autowiredBeanName, field.getType())) {
                                    this.cachedFieldValue = new AutowiredPlusAnnotationMergedBeanDefinitionPostProcessor.ShortcutDependencyDescriptor(
                                            desc, autowiredBeanName, field.getType());
                                }
                            }
                        }
                        else {
                            this.cachedFieldValue = null;
                        }
                        this.cached = true;
                    }
                }
            }
            if (value != null) {
                ReflectionUtils.makeAccessible(field);
                /**
                 * 反射将bean赋值
                 */
                field.set(bean, value);
            }
        }



    }



    /**
     * DependencyDescriptor variant with a pre-resolved target bean name.
     */
    @SuppressWarnings("serial")
    private static class ShortcutDependencyDescriptor extends DependencyDescriptor {

        private final String shortcut;

        private final Class<?> requiredType;

        public ShortcutDependencyDescriptor(DependencyDescriptor original, String shortcut, Class<?> requiredType) {
            super(original);
            this.shortcut = shortcut;
            this.requiredType = requiredType;
        }

        @Override
        public Object resolveShortcut(BeanFactory beanFactory) {
            return beanFactory.getBean(this.shortcut, this.requiredType);
        }
    }
}
