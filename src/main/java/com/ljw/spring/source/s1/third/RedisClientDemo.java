package com.ljw.spring.source.s1.third;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 未配置，不启用
 */
//@Component
public class RedisClientDemo implements InitializingBean {

    RedissonClient redissonClient;

    @Autowired
    Environment environment;

    @Override
    public void afterPropertiesSet() throws Exception {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + environment.getProperty("redission.ip"))
                .setPassword(environment.getProperty("redission.passwd"))
                .setConnectionPoolSize(Integer.parseInt(environment.getProperty("redission.connectionPoolSize")))
                .setConnectionMinimumIdleSize(Integer.parseInt(environment.getProperty("redission.cnnectionMinimumIdleSize")))
                ;
        this.redissonClient = Redisson.create(config);
    }


}
