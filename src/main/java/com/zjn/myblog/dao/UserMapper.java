package com.zjn.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjn.myblog.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
