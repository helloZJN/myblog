package com.zjn.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjn.myblog.dao.CommentMapper;
import com.zjn.myblog.entity.Comment;
import com.zjn.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        return commentMapper.selectList(new QueryWrapper<Comment>().eq("blog_id", blogId));
    }

    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        return commentMapper.insert(comment);
    }
}
