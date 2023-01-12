package com.summer.config.security;

import com.summer.services.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tubo
 * security配置类，拦截请求、登录转发、密码设定等
 * @date 2022/10/19
 */

@Configuration
@EnableWebSecurity
//开启权限注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //放行这个接口
                .antMatchers(getStrs()).permitAll()
                //.authenticated();所有/**都要认证
                .antMatchers("/test/**").authenticated()
                .and().addFilter(new TokenLoginFilter(authenticationManager()))
                .addFilter(new MyBasicAuthenticationFilter(authenticationManager()));
    }


    //注册加密方式到容器中
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new MyUserDetailService());
    }


    private String[] getStrs() {
        List<String> list = new ArrayList<>();
        /*******************SWAGGER start*************************************************************************/
        /*******************SWAGGER end*************************************************************************/

        list.add("/doc.html");
        list.add("/swagger-resources/**");
        list.add("/images/**");
        list.add("/webjars/**");
        list.add("/v2/api-docs");
        list.add("/configuration/ui");
        list.add("/configuration/security");
        list.add("/externalApi/**");
        list.add("/v2/api-docs-ext");
        list.add("/file/**");

        return list.toArray(new String[list.size()]);
    }
}
