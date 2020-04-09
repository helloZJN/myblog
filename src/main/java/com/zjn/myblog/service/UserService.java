package com.zjn.myblog.service;

import com.zjn.myblog.entity.User;

public interface UserService {
    User checkUser(String username, String password);
}
