package com.chengfei.book.controller;

import com.chengfei.book.pojo.Book;
import com.chengfei.book.pojo.Cart;
import com.chengfei.book.pojo.CartItem;
import com.chengfei.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    BookService bookService;
    @GetMapping("/client/addItem")
    @ResponseBody
    public Map<String,Object> addCartItem(@RequestParam("id") Integer id, HttpSession session, HttpServletRequest request){
        Book book = bookService.queryBookById(id);
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart= (Cart) session.getAttribute("cart");
        if(cart==null){
            session.setAttribute("cart",new Cart());
        }else {
            cart.addItem(cartItem);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("lastName",book.getName());
        assert cart != null;
        map.put("cartTotalCount",cart.getTotalCount());
        return map;
    }

}
