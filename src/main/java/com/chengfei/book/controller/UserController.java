package com.chengfei.book.controller;

import com.chengfei.book.pojo.User;
import com.chengfei.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户登录操作，用户名或密码错误回显用户名
     * @param username
     * @param password
     * @param map
     * @param session
     * @return
     */
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
            session.setAttribute("user",user);
            //重定向防止重复提交
            return "redirect:/login_success.html";
        }
    }

    /**
     * ajax方式判断用户名是否已存在，加@Responsebody将返回的数据封装为json格式给前端
     * @param username
     * @return
     */
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

    /**
     * 判断验证码，验证码正确保存用户并跳转到成功页面。不能用post方法，否则验证码错误的情况下
     * 无法请求转发到注册页面
     * @param username
     * @param password
     * @param email
     * @param map
     * @return
     */
    @GetMapping("/user/regist")
    public String regist(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         @RequestParam("code") String code,
                         HttpServletRequest request,
                         HttpSession session,
                         Map<String,Object>map){
        //从session中取出服务器生成的验证码
        Object kaptcha = request.getSession().getAttribute("kaptcha");
        if(kaptcha.equals(code)) {
            userService.registUser(new User(null, username, password, email));
            //页面显示用户名，保存在session中
            session.setAttribute("loginUser", username);
            return "redirect:/regist_success.html";
        }else {
            //验证码错误的情况下回显数据
            map.put("msg","验证码错误！");
            map.put("username",username);
            map.put("password",password);
            map.put("email",email);
            return "forward:/regist.html";
        }
    }


}
