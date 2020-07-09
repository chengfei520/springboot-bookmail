package com.chengfei.book.mapper;

import com.chengfei.book.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User queryUserByUsernameAndPassword(String username,String password);
    public User queryUserByUsername(String username);
}
