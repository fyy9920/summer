package com.summer.config.security;

import com.alibaba.fastjson.JSONObject;
import com.summer.common.Response;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author tubo
 * 登录认证过滤器,主要是AbstractAuthenticationProcessingFilter中的doFilter方法，实现参数的接收，登录是否成功的判断，attemptAuthentication和successfulAuthentication、unsuccessfulAuthentication
 * @date 2022/10/20
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;

    public TokenLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    //重写attemptAuthentication 用于接受登录的参数
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities
        UsernamePasswordAuthenticationToken authenResult = new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        return authenticationManager.authenticate(authenResult);
    }


    //登录成功过后，AbstractAuthenticationProcessingFilter中的doFilter方法会跳转到登录成功的方法，然后生成token
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        Claims claims = Jwts.claims();
        //生成token
        String token = Jwts.builder().setClaims(claims)
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "ykfSecret").compact();

        //将token存入redis,可以判断是否过期，这里就先不存了
        //Response tubResponse = new Response().success("Bearer " + token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("data", tubResponse);
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(jsonObject);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //失败后的请求
    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        System.out.println("登录失败");
    }

}
