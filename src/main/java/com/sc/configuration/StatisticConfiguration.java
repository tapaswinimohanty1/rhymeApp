package com.sc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sc.interceptor.StatisticInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.sc")
public class StatisticConfiguration extends WebMvcConfigurerAdapter  {

    @Autowired
    StatisticInterceptor statisticInterceptor ;



@Override
public void addInterceptors (InterceptorRegistry registry) {
   registry.addInterceptor(statisticInterceptor).addPathPatterns("/rhymes/**","/count");


}
}
