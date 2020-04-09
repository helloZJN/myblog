package com.zjn.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjn.myblog.dao.UserMapper;
import com.zjn.myblog.entity.User;
import com.zjn.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServideImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username", username).eq("password", password);
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
