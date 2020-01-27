package com.totoro.blog.framework.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @version 1.0
 * @className: ApplicationConfig
 * @description: Application annotation configuration
 * @author: Linh.Nguyen
 * @date: 26/01/2020
 */
@Configuration
//@EnableAspectJAutoProxy(exposeProxy    = true)
@MapperScan("com.totoro.blog.project.**.mapper")
public class ApplicationConfig {

}
