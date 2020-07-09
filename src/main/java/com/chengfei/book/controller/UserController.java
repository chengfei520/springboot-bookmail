package com.chengfei.book.controller;

import com.chengfei.book.mapper.UserMapper;
import com.chengfei.book.pojo.User;
import com.chengfei.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
    Map<String,Object>map, HttpSession session){
        User user=userService.login(new User(null,username,password,null));
        if(user==null){
            map.put("msg","用户名或密码错误！");
            map.put("username",username);
            return "forward:/login.html";
        }else {
            session.setAttribute("loginUser", username);
            //重定向防止重复提交
            return "redirect:/login_success.html";
        }
    }


}
