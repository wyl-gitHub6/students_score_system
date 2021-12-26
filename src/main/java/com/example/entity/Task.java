package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wyl
 * @date 2021年12月23日  10:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private Integer id;

    private String jobName;

    private String description;

    private String cronExpression;

    private String beanClass;

    /**
     * 开启 0
     * 关闭1
     */
    private String jobStatus;

    private String jobGroup;

}
