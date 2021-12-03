package com.example.entity;

import java.io.Serializable;
import lombok.*;

/**
 * (Teacher)
 *
 * @author wyl
 * @since 2021-10-06 18:43:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable {
    private static final long serialVersionUID = 578732600891727362L;

    private Integer teacherId;
    /**
     * 教师工号
     */
    private String teacherNum;

    private String teacherName;

    private Integer teacherAge;

    private String teacherCard;
    /**
     * 民族
     */
    private String teacherNational;

    private int teacherSex;

    private String teacherEmail;

    private String teacherImg;

    private String teacherPhone;

    private String teacherPassword;

    private String code;

}

