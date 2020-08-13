package com.chengfei.book.mapper;

import com.chengfei.book.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {

    public void saveOrderItem(OrderItem orderItem);
}
