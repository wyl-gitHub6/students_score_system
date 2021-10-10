package com.example.entity;

import java.io.Serializable;
import lombok.*;

/**
 * (Grade)
 *
 * @author wyl
 * @since 2021-10-07 18:01:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade implements Serializable {
    private static final long serialVersionUID = -40561028437948423L;

    private Integer gradeId;

    private String gradeNum;

    private String gradeName;

    private Professional professional;



}

