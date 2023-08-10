package com.example.carlosjr.cachevalidatemapper.config;

import com.github.benmanes.caffeine.cache.CaffeineSpec;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("team-cache");
        cacheManager.setCaffeineSpec(CaffeineSpec.parse("maximumSize=500,expireAfterAccess=30s"));
        return cacheManager;
    }

}
