package com.zjn.myblog.controller.admin;

import com.zjn.myblog.entity.Type;
import com.zjn.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;


    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1") Integer page,
                        Model model) {
        model.addAttribute("page", typeService.listType(page, 10));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {

        boolean existType = typeService.existType(type.getName());

        if (existType) {
            result.rejectValue("name", "nameError", "不能添加重复的分类");
        }

        if (result.hasErrors()) {
            return "admin/types-input";
        }

        int i = typeService.saveType(type);

        if (i > 0) {
            attributes.addFlashAttribute("message", "新增成功");
        } else {
            attributes.addFlashAttribute("message", "新增失败");
        }

        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {

        boolean existType = typeService.existType(type.getName());

        if (existType) {
            result.rejectValue("name", "nameError", "已存在该分类");
        }

        if (result.hasErrors()) {
            return "admin/types-input";
        }

        type.setId(id);
        typeService.updateType(type);
        attributes.addFlashAttribute("message", "操作成功");

        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }


}
