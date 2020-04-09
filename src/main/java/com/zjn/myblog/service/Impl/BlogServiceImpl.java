package com.zjn.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjn.myblog.dao.BlogMapper;
import com.zjn.myblog.dao.TypeMapper;
import com.zjn.myblog.entity.Blog;
import com.zjn.myblog.entity.Type;
import com.zjn.myblog.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        if ("".equals(blog.getFlag()) || blog.getFlag() == null) {
            blog.setFlag("原创");
        }
        Type type = typeMapper.selectById(blog.getTypeId());
        blog.setTypeName(type.getName());
        return blogMapper.insert(blog);
    }

    @Override
    public Blog getBlog(Long id) {
        return blogMapper.selectById(id);
    }

    @Override
    public Page<Blog> listBlog(Integer pageNum, Integer pageSize) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        Page<Blog> page = blogMapper.selectPage(new Page<Blog>(pageNum, pageSize), queryWrapper);
        PagePlus<Blog> pagePlus = new PagePlus<>();
        BeanUtils.copyProperties(page, pagePlus);
        pagePlus.pageCount = (page.getTotal() + page.getSize() - 1) / page.getSize();
        return pagePlus;
    }

    public class PagePlus<T> extends Page<T> {
        public long pageCount;
    }

    @Override
    public void updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        Type type = typeMapper.selectById(blog.getTypeId());
        blog.setTypeName(type.getName());
        blogMapper.updateById(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteById(id);
    }
}
