package com.man.mmall.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RedissonManager {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedissonClient getRedisson() {
        RedissonClient redissonClient = null;
        Config config = new Config();
        String[] nodes = redisProperties.getNodes().toArray(new String[redisProperties.getNodes().size()]);
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = "redis://" + nodes[i];
        }

        config.useClusterServers().setScanInterval(2000).addNodeAddress(nodes);
        redissonClient = Redisson.create(config);

        return redissonClient;
    }
}
