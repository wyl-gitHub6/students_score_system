package com.example.entity;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

/**
 * 课程
 *
 * @author Wangyl
 * @date 2022/04/01
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

    /**
     * 选修课人数限制
     */
    private Integer number;

    private Teacher teacher;

    private List<Student> studentList;

    private List<Classes> classesList;

    /**
     * 统计
     */
    private int value;
    private String name;

    /**
     * 选修课已选人数
     */
    private Integer checkNumber;
}

