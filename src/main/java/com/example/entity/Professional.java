package com.example.entity;

import java.io.Serializable;
import lombok.*;

/**
 * (Professional)
 *
 * @author wyl
 * @since 2021-10-07 18:02:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professional implements Serializable {
    private static final long serialVersionUID = 288513803958175148L;

    private Integer professionalId;

    private String professionalNum;

    private String professionalName;

    private College college;



}

