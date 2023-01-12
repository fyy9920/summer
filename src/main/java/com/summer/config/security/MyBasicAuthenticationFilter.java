package com.summer.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author tubo
 * 验证授权，token是否正确，及对应的接口是否能访问
 * @date 2022/10/20
 */
public class MyBasicAuthenticationFilter extends BasicAuthenticationFilter {


    public MyBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    //重写doFilterInternal
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //logger.error("请求地址：" + request.getRequestURI());
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            chain.doFilter(request, response);
            return;
        }
        String bearer = "Bearer ";
        if (StringUtils.isNotBlank(token) && token.startsWith(bearer)) {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);

    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims;
        //这里后面要捕获异常，判断token是否正确，
        try {
            claims = Jwts.parser().setSigningKey("ykfSecret").parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
            String user = claims.getSubject();

            //模拟权限信息，后面改成从redis获取
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

            if(CollectionUtils.isNotEmpty(authorities)) {
                /*//SimpleGrantedAuthority 存储权限信息的对象
                List<SimpleGrantedAuthority> auth = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());*/
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
        }catch (SignatureException signatureException) {

        }
        return null;
    }


}
