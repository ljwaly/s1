package com.ljw.spring.source.s1.aop.special;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class TransactionDemoService {

    private String flag = "begin";

    public void doTransNothing(){
        System.out.println("TransactionDemoService.doTransNothing");

        try {
            /**
             * 此时注解@Transactional无效，没有使用代理对象
             * this并不是本身的代理对象，而是一个普通对象
             */
            this.realTrans();//这种情况下，是不会走代理的
        } catch (Exception e) {
            System.out.println("回滚无效，无效flag = " + flag);
            flag = "begin";
        }



    }

    /**
     * 除了以下这种方式
     * 还可以自己注入自己，使用注解@Autowired TransactionDemoService transactionDemoService;//相当于循环依赖，自己注入自己
     * 还可以实现ApplicationContextAware，直接从spring容器中拿代理实例
     */
    public void doTransEffect(){
        System.out.println("TransactionDemoService.doTransEffect");

        try {
            /**
             * 此时需要@EnableAspectJAutoProxy开启
             * 并且属性exposeProxy=true有效，此时才能取到TransactionDemoService的代理对象
             */
            TransactionDemoService proxy = (TransactionDemoService) AopContext.currentProxy();

            proxy.realTrans();//这种情况下，是不会走代理的
        } catch (Exception e) {
            System.out.println("回滚有效，有效flag = " + flag);
        }
    }

    @Transactional
    public void realTrans() throws Exception {
        System.out.println("TransactionDemoService.realTrans");

        flag = "realTrans";

        throw new Exception();

    }
}
