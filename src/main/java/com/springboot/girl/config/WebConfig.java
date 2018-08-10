package com.springboot.girl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//@Configuration
@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	UserArgumentResolver userArgumentResolver;
	@Autowired
	LoginInterceptor loginInterceptor;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userArgumentResolver);
	}

	/**
	 * excludePathPatterns排除拦截的路径
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).excludePathPatterns("/login/**")
				.excludePathPatterns("/error")
				.excludePathPatterns("/static/**")
				.excludePathPatterns("/js/**")
				.excludePathPatterns("/layer/**")
				.excludePathPatterns("/bootstrap/**").excludePathPatterns("/img/**")
				.excludePathPatterns("/jquery-validation/**");
	}

	/**
	 * 配置静态资源路径
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
}
