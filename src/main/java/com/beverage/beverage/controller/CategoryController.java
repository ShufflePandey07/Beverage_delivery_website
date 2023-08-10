package com.beverage.beverage.controller;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beverage.beverage.Service.CategoryService;
import com.beverage.beverage.dto.CategoryDto;
import com.beverage.beverage.entity.Category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/save")
    public String saveData(@Valid CategoryDto categoryDto) {
        categoryService.saveData(categoryDto);
        return "redirect:/category/get";
    }

    @GetMapping("/get")
    public String getAllData(Model model) {
        model.addAttribute("categories", categoryService.getAllData());

        return "viewCategory";
    }

    @PostMapping("/delete/{id}")
    public String deleteData(@PathVariable @Param("id") int id) {
        categoryService.deleteData(id);
        return "redirect:/category/get";
    }

    @PostMapping("/update")
    public String updateData(@Valid CategoryDto categoryDto) {
        categoryService.saveData(categoryDto);
        return "Data Updated";
    }

}
