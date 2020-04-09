package com.zjn.myblog.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjn.myblog.entity.Blog;
import com.zjn.myblog.entity.Type;
import com.zjn.myblog.entity.User;
import com.zjn.myblog.service.BlogService;
import com.zjn.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;


    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1") Integer page,
                        Model model) {
        Page<Blog> blogPage = blogService.listBlog(page, 10);

        List<Type> typeList = typeService.listType(1, 1000).getRecords();
        Map<Long, String> collect = typeList.stream().collect(
                Collectors.toMap(Type::getId, Type::getName, (key1, key2) -> key2));

        model.addAttribute("page", blogPage);
        model.addAttribute("typeMap", collect);
        return "admin/blogs";
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("types", typeService.listType(1, 1000).getRecords());
        Blog blog = blogService.getBlog(id);
        model.addAttribute("blog", blog);
        Type type = new Type(blog.getTypeId(), blog.getTypeName());
        model.addAttribute("type", type);
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listType(1, 1000).getRecords());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String post(@Valid Blog blog, RedirectAttributes attributes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        blog.setUserId(user.getId());
        if (blog.getId() != null) {
            blogService.updateBlog(blog);
        } else {
            int i = blogService.saveBlog(blog);
            if (i > 0) {
                attributes.addFlashAttribute("message", "新增成功");
            } else {
                attributes.addFlashAttribute("message", "新增失败");
            }
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

}
