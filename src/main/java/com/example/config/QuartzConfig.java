package com.example.config;

import com.example.quartz.JobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wyl
 * 任务调度配置
 * @date 2021年12月23日  10:21
 */
@Configuration
public class QuartzConfig {


    @Autowired
    private JobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        try {
            bean.setOverwriteExistingJobs(true);
            bean.setQuartzProperties(quartzProperties());
            bean.setJobFactory(jobFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean(name="scheduler")
    public Scheduler scheduler(){
        return schedulerFactoryBean().getScheduler();
    }
}
