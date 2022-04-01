package com.example.entity;

import java.io.Serializable;

import lombok.*;

/**
 * 排课
 *
 * @author wyl
 * @since 2021-10-13 22:58:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Arrang implements Serializable {
    private static final long serialVersionUID = 296398248769438221L;

    private Classes classes;

    private Course course;

}

