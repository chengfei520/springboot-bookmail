package com.chengfei.book.controller;

import com.chengfei.book.pojo.Book;
import com.chengfei.book.pojo.Cart;
import com.chengfei.book.pojo.CartItem;
import com.chengfei.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    BookService bookService;
    @GetMapping("/client/showItems")
    public String showItems(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null){
            session.setAttribute("cart",new Cart());
            return "redirect:/cart.html";
        }
        return "redirect:/cart.html";
    }
    @PostMapping("/client/addItem")
    @ResponseBody
    public Map<String,Object> addCartItem(@RequestParam("id") Integer id, HttpSession session){
        Book book = bookService.queryBookById(id);
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart= (Cart) session.getAttribute("cart");
        if(cart==null){
            session.setAttribute("cart",new Cart());
            ((Cart) session.getAttribute("cart")).addItem(cartItem);
        }else {
            cart.addItem(cartItem);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("lastName",book.getName());
        if(cart!=null) {
            map.put("cartTotalCount", cart.getTotalCount());
        }else {
            map.put("cartTotalCount",0);
        }
        return map;
    }
    @GetMapping("/client/deleteItem")
    public String deleteItem(HttpSession session,@RequestParam("id") Integer id){
        Cart cart = (Cart) session.getAttribute("cart");
        cart.deleteItem(id);
        return "redirect:/cart.html";
    }

    @GetMapping("/client/clearItems")
    public String clearItems(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        cart.clear();
        return "redirect:/cart.html";
    }
    @GetMapping("/client/updateItem")
    public String updateItem(HttpSession session,@RequestParam("id") Integer id,@RequestParam("count") Integer count){
        Cart cart = (Cart) session.getAttribute("cart");
        cart.updateCount(id,count);
        return "redirect:/cart.html";
    }
}
