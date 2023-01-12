package com.summer.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tubo
 * 测试security类
 * @date 2022/10/19
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @PostMapping("/add")
    //@PreAuthorize("hasAuthority('user:add')")
    public String testAdd(){
        return "新增成功";
    }


    @PostMapping("/update")
    @PreAuthorize("hasAuthority('user:update')")
    public String testUpdate(HttpServletRequest request){
        String token = request.getHeader("token");
        System.out.println(token);
        return "更新成功";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('user:delete')")
    public String testDelete(){
        return "删除成功";
    }

}
