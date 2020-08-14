package com.chengfei.book.mapper;

import com.chengfei.book.pojo.Order;
import com.chengfei.book.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    public void saveOrder(Order order);

    public List<Order> queryOrders();

    public List<OrderItem> queryItemsByOrderId(String orderId);
    public void changeOrderStatus(String orderId);

    public List<Order> queryOrderByUserId(Integer userId);

    public void changeOrderStatusToReceived(String orderId);
}
