package com.totoro.blog.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @version 1.0
 * @className: SpringUtils
 * @description: Spring tool classes for easy access to beans in non-spring management environments
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
public class SpringUtils implements BeanFactoryPostProcessor {
    private static ConfigurableListableBeanFactory beanFactory;
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    /**
     * Get bean
     *
     * @param nm Name
     * @param <T> class
     * @return An instance of a bean registered with the given name
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String nm) throws BeansException{
        return (T) beanFactory.getBean(nm);
    }

    /**
     *
     * @param clz
     * @param <T> class
     * @return bean
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> clz) throws BeansException{
        return beanFactory.getBean(clz);
    }
}
