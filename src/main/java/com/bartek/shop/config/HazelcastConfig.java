package com.bartek.shop.config;

import com.bartek.shop.model.dao.Product;
import com.hazelcast.config.*;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class HazelcastConfig {

    @Bean
    public Config configHazelcast() {
        Config config = new Config()
                .addMapConfig(new MapConfig()
                        .setName("product")
                        .setEvictionConfig(new EvictionConfig()
                                .setSize(10)
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE))
                        .setTimeToLiveSeconds(3600));

        config.getSerializationConfig().addDataSerializableFactory(1, id -> id == 1 ? new Product() : null);

        return config;
    }
}