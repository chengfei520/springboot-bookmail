package com.chengfei.book.mapper;

import com.chengfei.book.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    public void saveOrder(Order order);
}
