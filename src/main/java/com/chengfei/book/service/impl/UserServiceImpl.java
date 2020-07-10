package com.chengfei.book.service.impl;

import com.chengfei.book.mapper.UserMapper;
import com.chengfei.book.pojo.User;
import com.chengfei.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User login(User user){
        return userMapper.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void registUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public boolean existsUsername(String username) {
        if(userMapper.queryUserByUsername(username)!=null){
            return true;
        }
        return false;
    }
}
