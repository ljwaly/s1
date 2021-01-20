package com.ljw.spring.source.s1.util;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SPELParserUtil {

    private static ExpressionParser parser = new SpelExpressionParser();


    /**
     * 这个是解析SPEL表达式的方法
     *
     *
     * @param key ： #key
     * @param paramNames ：
     * @param args
     * @return
     */
    public static String getValue(String key, String[] paramNames, Object[] args){

        if (args == null || args.length <= 0) {
            return null;
        }

        /**
         * 获取关于key的spel解析器
         */
        Expression expression = parser.parseExpression(key);


        /**
         * 初始化标准的编译参数key和value
         *
         * 建立了key和value的关系
         */
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        for (int i = 0; i < args.length; i++) {
            standardEvaluationContext.setVariable(paramNames[i], args[i]);
        }

        /**
         * 通过key的解析器，去标准列表里面获取对应类型的对象的value
         */
        return expression.getValue(standardEvaluationContext, String.class);

    }


}
