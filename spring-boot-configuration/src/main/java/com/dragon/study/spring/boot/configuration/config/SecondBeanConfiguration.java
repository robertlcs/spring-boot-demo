package com.dragon.study.spring.boot.configuration.config;

import com.dragon.study.spring.boot.configuration.bean.SecondBean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 16/10/8.
 */
@Configuration
@Slf4j
public class SecondBeanConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public SecondBean secondBean() {
    log.info("log second bean");
    return new SecondBean("secondBean", 2);
  }
}
