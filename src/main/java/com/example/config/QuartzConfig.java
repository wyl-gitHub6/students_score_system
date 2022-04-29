package com.example.config;

import com.example.quartz.JobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Properties;

/**
 * 任务调度配置
 *
 * @author Wangyl
 * @date 2022/04/27
 */
@Configuration
public class QuartzConfig {

    @Resource
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
