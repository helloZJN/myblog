package com.zjn.myblog;

import com.zjn.myblog.dao.BlogMapper;
import com.zjn.myblog.entity.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyblogApplicationTests {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    void contextLoads() {
        List<Blog> blogs = blogMapper.selectList(null);
        blogs.forEach(System.out::println);
    }

}
