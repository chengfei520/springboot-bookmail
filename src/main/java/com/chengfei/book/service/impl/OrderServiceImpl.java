package com.chengfei.book.service.impl;

import com.chengfei.book.mapper.BookMapper;
import com.chengfei.book.mapper.OrderItemMapper;
import com.chengfei.book.mapper.OrderMapper;
import com.chengfei.book.pojo.*;
import com.chengfei.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public String createOrder(Cart cart, Integer userId) {
//订单号是惟一的
        String orderId=System.currentTimeMillis()+""+userId;
        Order order=new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderMapper.saveOrder(order);
//        int i=12/0;
        //遍历购物车中每一个商品项转换成订单项保存到数据库
        for(Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()){
            //获取购物车中的商品项
            CartItem cartItem=entry.getValue();
            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemMapper.saveOrderItem(orderItem);
            //更新库存和销量
            Book book=bookMapper.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookMapper.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return orderId;
    }
}
