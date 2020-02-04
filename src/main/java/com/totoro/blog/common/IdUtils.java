package com.totoro.blog.common;

/**
 * @author linhnguyen
 * @version 1.0
 * @classname IdUtils
 * @description ID generate tool class
 * @date 28/01/2020
 */
public class IdUtils {
    public IdUtils() {
    }

    /**
     * Get random UUID
     *
     * @return Random UUID
     */
    public static String randomUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * Get random UUID, use better performance ThreadLocalRandom to generate UUID
     *
     * @return Random UUID
     */
    public static String fastUUID(){
        return UUID.fastUUID().toString();
    }
}
