package com.example.security;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wyl
 */
@Service
public class DetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultPasswordEncoder passwordEncoder;

    private User u = null;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasLength(username)){
            throw new NullPointerException("空数据");
        }
        User user = userService.findByUserNum(username);
        if (ObjectUtils.isEmpty(user)){
            throw new UsernameNotFoundException("用户不合法！");
        }
        u = user;
        List<GrantedAuthority> author = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        return new org.springframework.security.core.userdetails.User(user.getUserNum(),
                passwordEncoder.encode(user.getUserPassword()),author);
    }

    public User getUsers(){
        return u;
    }
}
