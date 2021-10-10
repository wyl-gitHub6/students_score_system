package com.example.entity;

import java.io.Serializable;
import lombok.*;

/**
 * (College)
 *
 * @author wyl
 * @since 2021-10-07 18:01:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class College implements Serializable {
    private static final long serialVersionUID = 817772518359758524L;

    private Integer collegeId;

    private String collegeNum;

    private String collegeName;



}

