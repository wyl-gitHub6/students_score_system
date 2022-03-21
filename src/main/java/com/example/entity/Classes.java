package com.example.entity;

import java.io.Serializable;
import java.util.List;

import lombok.*;

/**
 * (Classes)
 *
 * @author wyl
 * @since 2021-10-09 11:02:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classes implements Serializable {
    private static final long serialVersionUID = -68451697061615279L;

    private Integer classesId;

    private String classesNum;

    private String classesName;
    /**
     * 总人数
     */
    private Integer classesAllNumber;
    /**
     * 当前人数
     */
    private Integer classesNumber;
    /**
     * 班级编码
     */
    private String classesCode;

    /**
     * 状态 0--未满 1--满
     */
    private Integer classesState;
    /**
     * 外键ID
     */
    private Grade grade;

    private Teacher teacher;

    private List<Course> courseList;

    private Course course;

}

