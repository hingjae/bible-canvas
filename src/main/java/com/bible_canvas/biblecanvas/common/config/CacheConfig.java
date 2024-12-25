package com.bible_canvas.biblecanvas.common.config;

import com.bible_canvas.biblecanvas.bible.entity.BibleTitle;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        javax.cache.CacheManager cacheManager = Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider")
                .getCacheManager();

        CacheConfiguration<String, BibleTitle> cacheConfig = createCacheConfig(String.class, BibleTitle.class, 10, Duration.ofMinutes(5));

        createOrReplaceCache(cacheManager, "bibleTitleCache", cacheConfig);

        return new JCacheCacheManager(cacheManager);
    }

    // 동적으로 캐시 설정을 생성하는 메서드
    private  <K, V> CacheConfiguration<K, V> createCacheConfig(Class<K> keyType, Class<V> valueType, long maxSize, Duration ttl) {
        return CacheConfigurationBuilder
                .newCacheConfigurationBuilder(keyType, valueType, ResourcePoolsBuilder.heap(maxSize))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(ttl))  // TTL 설정
                .build();
    }

    // 캐시 이름과 설정을 받아 캐시를 생성하는 메서드
    private  <K, V> void createOrReplaceCache(javax.cache.CacheManager cacheManager, String cacheName, CacheConfiguration<K, V> cacheConfig) {
        cacheManager.destroyCache(cacheName);
        cacheManager.createCache(cacheName, Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfig));
    }
}
