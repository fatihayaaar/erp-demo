package com.fayardev.erpdemo.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config hazelcastConfig() {
        Config config = new Config();
        config.setClusterName("my-cluster");

        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("hibernate");
        mapConfig.setTimeToLiveSeconds(3600);
        config.addMapConfig(mapConfig);

        return config;
    }
}
