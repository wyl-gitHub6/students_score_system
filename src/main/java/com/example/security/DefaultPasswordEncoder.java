package com.example.security;

import cn.hutool.crypto.SecureUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码处理
 * @author wyl
 * @date 2021年12月06日  14:53
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder (){
        this(-1);
    }

    public DefaultPasswordEncoder (int strength){
    }

    /**
     * 加密
     * @author MVT-wyl
     * @date 15:02 2021/12/6
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return SecureUtil.md5(rawPassword.toString());
    }

    /**
     * 解密对比
     * @author MVT-wyl
     * @date 15:02 2021/12/6
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(SecureUtil.md5(rawPassword.toString()));
    }
}
