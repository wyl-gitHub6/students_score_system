package com.example.entity;

import java.io.Serializable;
import lombok.*;

/**
 * (User)
 *
 * @author wyl
 * @since 2021-10-28 16:16:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 306794245072826021L;

    private Integer userId;

    private String userNum;

    private String userName;

    private String userPassword;

    private Integer userAge;

    private String userEmail;

    private String userPhone;

    private Integer userSex;

    private String userImg;

    private String userNational;

    private String userCard;

    /**
     * 验证码  不做字段
     */
    private String code;



}

