package com.example.security;

import cn.hutool.json.JSONUtil;
import com.example.entity.User;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wyl
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private DetailsService detailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        /**
         * Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();获取用户名
         */
        User users = detailsService.getUsers();
        outputStream.write(JSONUtil.toJsonStr(Result.success(users,"登录成功!")).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
