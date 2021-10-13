package com.example.entity;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

/**
 * (Course)
 *
 * @author wyl
 * @since 2021-10-11 22:26:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {
    private static final long serialVersionUID = 802356962872829370L;

    private Integer courseId;

    private String courseName;

    private String courseNum;
    /**
     * 课程描述
     */
    private String courseDesc;
    /**
     * 课程类别 0-必修课  1-选修课
     */
    private Integer courseState;

    private String courseImg;
    /**
     * 学分
     */
    private Integer courseCredit;
    /**
     * 开始日期
     */
    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    private Date startTime;
    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    private Date endTime;

    private Teacher teacher;



}

