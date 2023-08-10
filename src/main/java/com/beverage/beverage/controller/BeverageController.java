package com.beverage.beverage.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beverage.beverage.Service.BeverageService;
import com.beverage.beverage.Service.CategoryService;
import com.beverage.beverage.dto.BeverageDto;
import com.beverage.beverage.entity.Beverage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RequestMapping("/beverage")
@Controller
public class BeverageController {
    private final CategoryService categoryService;
    private final BeverageService beverageService;

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("categories", categoryService.getAllData());
        return "addItem";
    }

    @PostMapping("/save")
    public String save(@Valid BeverageDto beverageDto) throws IOException {
        beverageService.saveData(beverageDto);
        return "redirect:/beverage/add";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        beverageService.deleteBeverage(id);
        return "redirect:/beverage/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<Beverage> beverages = beverageService.getAllData();
        model.addAttribute("beverages", beverages.stream().map(beverage -> Beverage.builder()
                .id(beverage.getId())
                .name(beverage.getName())
                .description(beverage.getDescription())
                .price(beverage.getPrice())
                .imageBase64(getImageBase64(beverage.getImage()))
                .category(beverage.getCategory())
                .build()));
        return "viewItem";
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/beverage_img/";
        File file = new File(filePath + fileName);
        System.out.println(file.getAbsolutePath());
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }
}
