package com.example.carlosjr.ooconcepts.config;

import com.example.carlosjr.ooconcepts.lifecycle.PrototypeBean;
import com.example.carlosjr.ooconcepts.lifecycle.RequestBean;
import com.example.carlosjr.ooconcepts.lifecycle.SingletonBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Configuration
public class BeanConfigurer {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrototypeBean prototypeBean(){
        return new PrototypeBean();
    }

    @Bean
    @Scope(value = "request", proxyMode = TARGET_CLASS)
    public RequestBean requestBean(){
        return new RequestBean();
    }

    @Bean
    public SingletonBean singletonBean(){
        return new SingletonBean();
    }


}
