package com.cutty_pet.cutty_pet.common.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class GuavaCacheManager {

    // 单例实例
    private static final GuavaCacheManager INSTANCE = new GuavaCacheManager();

    // Guava Cache 实例
    private final Cache<String, Object> cache;

    // 私有构造器
    private GuavaCacheManager() {
        // 初始化缓存，设置最大条目数和过期时间
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    // 获取单例实例
    public static GuavaCacheManager getInstance() {
        return INSTANCE;
    }

    // 存入缓存
    public void put(String key, Object value) {
        cache.put(key, value);
    }

    // 获取缓存值，并转换为指定类型
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> type) {
        Object value = cache.getIfPresent(key);
        return value == null ? null : type.cast(value);
    }

    // 删除某个缓存项
    public void evict(String key) {
        cache.invalidate(key);
    }

    // 清空所有缓存
    public void clear() {
        cache.cleanUp(); // 清理已过期或无效的缓存
        cache.invalidateAll();
    }

    // 获取原始 Cache 对象，用于高级操作
    public Cache<String, Object> getNativeCache() {
        return cache;
    }
}