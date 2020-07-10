package com.chengfei.book.controller;

import com.chengfei.book.pojo.User;
import com.chengfei.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/user/userjudge",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> usernameAjax(@RequestParam("username") String username)
    {
        //此Map不能放在方法参数中，不然一直报错！放在方法参数中适用于将值返回给页面
        HashMap<String, Object> map = new HashMap<>();
        if(userService.existsUsername(username)){
            map.put("existUser",username);
        }else {
            map.put("existUser",null);
        }
        return map;
    }
    @PostMapping("/user/regist")
    public String regist(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         Map<String,Object>map){
            userService.registUser(new User(null,username,password,email));
            return "redirect:/regist_success.html";
    }


}
