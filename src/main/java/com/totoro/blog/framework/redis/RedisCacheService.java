package com.totoro.blog.framework.redis;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author linhnguyen
 * @version 1.0
 * @classname RedisCacheService
 * @description Spring redis utils class
 * @date 28/01/2020
 */
@Component
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class RedisCacheService {
    private final RedisTemplate redisTemplate;

    public RedisCacheService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Cache basic objects, includes: Integer, String, entity classes, etc.
     *
     * @param key Cached key
     * @param value Cached value
     * @return Cached object
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value){
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        return operation;
    }

    /**
     * Cache basic objects, includes: Integer, String, entity classes, etc.
     *
     * @param key Cached key
     * @param value Cached value
     * @param timeOut Time
     * @param timeUnit Time granularity
     * @return Cache object
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value, Integer timeOut, TimeUnit timeUnit){
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, timeOut, timeUnit);
        return operation;
    }

    /**
     * Get the cached base object.
     *
     * @param key Cache key
     * @return Cache key data
     */
    public <T> T getCacheObject(String key){
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * Delete single object
     *
     * @param key Cache key
     */
    public void deleteObject(String key){
        redisTemplate.delete(key);
    }

    /**
     * Delete collection object
     *
     * @param collection collection cache key
     */
    public void deleteObject(Collection collection){
        redisTemplate.delete(collection);
    }

    /**
     * Cache list data
     *
     * @param key Cached key
     * @param dataList List data to be cached
     * @return Cached object
     */
    public <T> ListOperations<String, T> setCacheList(String key, List<T> dataList){
        ListOperations listOperations = redisTemplate.opsForList();
        if (null != dataList){
            for (T t : dataList) {
                listOperations.leftPush(key, t);
            }
        }
        return listOperations;
    }

    /**
     * Get cached list object
     *
     * @param key Cache key
     * @return Cache key data
     */
    public <T> List<T> getCacheList(String key){
        List<T> dataList = new ArrayList<>();
        ListOperations<String, T> listOperations = redisTemplate.opsForList();
        Long size = listOperations.size(key);

        for (int i = 0; i < size; i++) {
            dataList.add(listOperations.index(key, i));
        }
        return dataList;
    }

    /**
     * Cache set
     *
     * @param key       Cache key
     * @param dataSet   Cached data
     * @return          Objects that cache data
     */
    public <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet){
        BoundSetOperations<String, T> setOperations = redisTemplate.boundSetOps(key);
        for (T t : dataSet) {
            setOperations.add(t);
        }

        return setOperations;
    }

    /**
     * Get cached set
     *
     * @param key   Cache key
     * @return      Objects that cache data
     */
    public <T> Set<T> getCacheSet(String key){
        Set<T> dataSet = new HashSet<>();
        BoundSetOperations<String, T> operations = redisTemplate.boundSetOps(key);
        Long size = operations.size();
        for (int i = 0; i < size; i++) {
            dataSet.add(operations.pop());
        }
        return dataSet;
    }

    /**
     * Cache map
     *
     * @param key Cache map
     * @param dataMap Data of Map
     * @return Cache Map
     */
    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap){
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap){
            for (Map.Entry<String, T> entry : dataMap.entrySet()){
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * Get cache map
     *
     * @param key Cache key
     * @return Cache map
     */
    public <T> Map<String, T> getCacheMap(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * Get cached base object list
     *
     * @param pattern String prefix
     * @return Object list
     */
    public Collection<String> keys(String pattern){
        return redisTemplate.keys(pattern);
    }

    /*TODO implement here*/
    //public List<RedisInfo>

    /**
     * Return to Redis Template
     *
     * @return Redis template
     */
    public RedisTemplate getRedisTemplate(){
        return redisTemplate;
    }
}
