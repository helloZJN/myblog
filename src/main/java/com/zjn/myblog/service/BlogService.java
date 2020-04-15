package com.zjn.myblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjn.myblog.entity.Blog;

public interface BlogService {
    int saveBlog(Blog blog);

    Blog getBlog(Long id);

    Page<Blog> listBlog(Integer pageNum, Integer pageSize, Integer isIndex);

    Page<Blog> listBlogByTypeId(Integer pageNum, Integer pageSize, Long typeId);

    void updateBlog(Blog blog);

    void updateViews(Blog blog);

    void deleteBlog(Long id);
}
