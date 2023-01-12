package com.summer.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author tubo
 * @date 2022/10/20
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    //通过用户名去获取用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //登录成功回去返回token也就是jwt
        UserDetails userDetails = User.withUsername("tubo").password("$2a$10$LiDQNQwmSiNzyz7cgvReSO5tra8ccO00JcKFGX7UDgHHsPWVx/UhW").authorities("user:update").build();
        return userDetails;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }
}
