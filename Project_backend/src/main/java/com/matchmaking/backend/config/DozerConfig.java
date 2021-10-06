package com.matchmaking.backend.config;


import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper mapper(){
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }
}
