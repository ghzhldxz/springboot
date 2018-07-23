package com.springboot.girl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description 引入外部xml配置信息
 * @Author GuanHuizhen
 * @Date 2018/7/23
 */
@Configuration
@ImportResource(locations={"classpath:applicationContext.xml"})
public class ConfigClass {

}