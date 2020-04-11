package com.zjn.myblog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjn.myblog.entity.Blog;
import com.zjn.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        Model model) {
        Page<Blog> blogPage = blogService.listBlog(page, 10);
        model.addAttribute("page", blogPage);

        return "index";
    }

}
