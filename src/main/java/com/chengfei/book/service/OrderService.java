package com.chengfei.book.service;

import com.chengfei.book.pojo.Cart;
import com.chengfei.book.pojo.Order;
import com.chengfei.book.pojo.OrderItem;
import org.springframework.aop.target.LazyInitTargetSource;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

    public List<Order> showAllOrders();

    public List<OrderItem> showOrderDetails(String orderId);

    public void sendOrder(String orderId);

    public List<Order> showMyOrders(Integer userId);

    public void receiveOrder(String orderId);
}
