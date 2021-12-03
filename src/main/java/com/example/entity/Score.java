package com.example.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.*;

/**
 * (Score)
 *
 * @author wyl
 * @since 2021-10-13 22:19:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score implements Serializable {
    private static final long serialVersionUID = -28876713099324380L;

    private Integer scoreId;

    private Integer studentId;

    private Student student;

    private Course course;

    private Integer courseId;

    /**
     * 选课状态  0选择 1取消
     */
    private Integer state;
    /**
     * 成绩状态  0无成绩  1有成绩
     */
    private Integer gradeState;
    /**
     * 获得学分
     */
    private Integer credit;
    /**
     * 平时成绩
     */
    private Double usualGrade;
    /**
     * 考试成绩
     */
    private Double testGrade;
    /**
     * 最终成绩
     */
    private Double scoreGrade;

    private Integer count;

}

