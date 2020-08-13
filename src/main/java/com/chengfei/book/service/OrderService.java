package com.chengfei.book.service;

import com.chengfei.book.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
