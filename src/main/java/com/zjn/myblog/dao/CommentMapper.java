package com.zjn.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjn.myblog.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}
