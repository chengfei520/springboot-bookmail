package com.chengfei.book.controller;

import com.chengfei.book.pojo.Cart;
import com.chengfei.book.pojo.Order;
import com.chengfei.book.pojo.OrderItem;
import com.chengfei.book.pojo.User;
import com.chengfei.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/createOrder")
    public String createOrder(HttpSession session){
        Cart cart= (Cart) session.getAttribute("cart");
        User user= (User) session.getAttribute("user");
        if(user==null){
            return "forward:/login.html";
        }
        Integer userId=user.getId();
        String orderId= null;

        orderId = orderService.createOrder(cart,userId);
        //防止表单重复提交
        session.setAttribute("orderId",orderId);
        return "redirect:/checkout.html";

    }
    @GetMapping("/showOrders")
    public String showAllOrders(Model model){
        List<Order> orders = orderService.showAllOrders();
        model.addAttribute("orders",orders);
        return "forward:/order_manager.html";
    }
    @GetMapping("/showOrderDetails")
    public String showOrderDetails(@RequestParam("orderId") String orderId,Model model){
        List<OrderItem> orderItems=orderService.showOrderDetails(orderId);
        model.addAttribute("orderItems",orderItems);
        return "forward:/order_detail.html";
    }
    @GetMapping("/sendOrder")
    public String sendOrder(@RequestParam("orderId") String orderId,Model model){
        orderService.sendOrder(orderId);
        model.addAttribute("orderId",orderId);
        return "forward:/send_order.html";
    }
    @GetMapping("/myOrders")
    public String showMyOrders(@RequestParam("userId") Integer userId,Model model){
        List<Order> orders=orderService.showMyOrders(userId);
        model.addAttribute("orders",orders);
        return "forward:/order.html";
    }
    @GetMapping("/receiveOrder")
    public String receiveOrder(@RequestParam("orderId") String orderId,Model model){
        orderService.receiveOrder(orderId);
        model.addAttribute("orderId",orderId);
        return "forward:/receive_order.html";
    }
}
