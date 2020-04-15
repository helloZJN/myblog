package com.zjn.myblog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjn.myblog.entity.Blog;
import com.zjn.myblog.entity.Type;
import com.zjn.myblog.service.BlogService;
import com.zjn.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, @RequestParam(defaultValue = "1") Integer page, Model model) {
        List<Type> types = typeService.listType(1, 10000).getRecords();
        if (id == -1) {
            id = types.get(0).getId();
        }
        Page<Blog> blogPage = blogService.listBlogByTypeId(page, 10, id);

        model.addAttribute("types", types);
        model.addAttribute("page", blogPage);
        model.addAttribute("activeTypeId", id);

        return "types";
    }
}
