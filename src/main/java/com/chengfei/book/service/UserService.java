package com.chengfei.book.service;

import com.chengfei.book.pojo.User;


public interface UserService {
    public User login(User user);
    public void registUser(User user);
    public boolean existsUsername(String username);
}
