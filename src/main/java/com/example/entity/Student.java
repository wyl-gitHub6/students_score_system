package com.example.entity;

import java.io.Serializable;
import lombok.*;

/**
 * (Student)
 *
 * @author wyl
 * @since 2021-10-10 11:26:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = 635930607300555389L;

    private Integer studentId;

    private Integer studentNum;

    private String studentName;

    private Integer studentSex;

    private Integer studentAge;
    /**
     * 民族
     */
    private String studentNational;

    private String studentEmail;

    private String studentCard;

    private String studentPhone;

    private String studentImg;
    /**
     * 班级外键
     */
    private Classes classes;

    private String studentPassword;



}

