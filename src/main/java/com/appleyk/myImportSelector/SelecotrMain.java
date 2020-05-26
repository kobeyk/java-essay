package com.appleyk.myImportSelector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableService
public class SelecotrMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SelecotrMain.class, args);
        TestService testService = context.getBean(TestService.class);
        testService.test();

        /**
         * SpringBoot Starter组件 实现自动装配的原理
         * 1、
         * @EnableAutoConfiguration
         * public @interface SpringBootApplication {}
         *
         * 2、
         * @Import({AutoConfigurationImportSelector.class})
         * public @interface EnableAutoConfiguration {}
         *
         * 3、AutoConfigurationImportSelector
         * public String[] selectImports(AnnotationMetadata annotationMetadata) {
         *         if (!this.isEnabled(annotationMetadata)) {
         *             return NO_IMPORTS;
         *         } else {
         *             AutoConfigurationMetadata autoConfigurationMetadata = AutoConfigurationMetadataLoader.loadMetadata(this.beanClassLoader);
         *             AutoConfigurationImportSelector.AutoConfigurationEntry autoConfigurationEntry = this.getAutoConfigurationEntry(autoConfigurationMetadata, annotationMetadata);
         *             return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
         *         }
         *     }
         *
         * 4、this.getAutoConfigurationEntry（）； // 获取自动配置条目
         * 5、
         * protected AutoConfigurationImportSelector.AutoConfigurationEntry getAutoConfigurationEntry(AutoConfigurationMetadata autoConfigurationMetadata, AnnotationMetadata annotationMetadata) {
         *         if (!this.isEnabled(annotationMetadata)) {
         *             return EMPTY_ENTRY;
         *         } else {
         *             List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
         *             .... .... ....
         *         }
         *     }
         *
         * 6、META-INF/spring.factories 这就是一种利用SPI（服务发现机制）机制实现的类加载（反射）
         *   protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
         *         List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
         *         Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.");
         *         return configurations;
         *     }
         *
         *     SpringBoot 官方spring-boot-starter-xxxjar包中是没有META-INF/spring-factories文件的
         *     因为在spring-boot-autoconfigure包中的META-INF/spring.factories文件中已经统一定义过了
         *     什么时候注入bean，是通过@ConditionOnClass注解来控制的，比如redis的配置类如下
         * @Configuration
         * @ConditionalOnClass({RedisOperations.class}) // 主要通过这个控制，
         * @EnableConfigurationProperties({RedisProperties.class})
         * @Import({LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class})
         * public class RedisAutoConfiguration {
         *     public RedisAutoConfiguration() {
         *     }
         *     ... ... ...
         * }
         */
    }

}
