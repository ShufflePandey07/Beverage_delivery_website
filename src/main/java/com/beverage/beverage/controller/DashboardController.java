package com.beverage.beverage.controller;

import com.beverage.beverage.Service.BeverageService;
import com.beverage.beverage.Service.CategoryService;
import com.beverage.beverage.entity.Beverage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final BeverageService beverageService;
    private final CategoryService categoryService;
    @GetMapping("")
    public String dashboard(Model model) {
        List<Beverage> beverages=beverageService.getLatestThreeData();
        model.addAttribute("beverages", beverages.stream().map(beverage -> Beverage.builder()
                .id(beverage.getId())
                .name(beverage.getName())
                .price(beverage.getPrice())
                .category(beverage.getCategory())
                .imageBase64(getImageBase64(beverage.getImage()))
                .build())

        );
        return "dashboard";

    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int id, @RequestParam(defaultValue = "") String partialName) {
        int totalItems;
        if(id==0) {
            totalItems = beverageService.countAllItems(partialName);
        }
        else{
            totalItems = beverageService.countAllItemsByCategoryId(id,partialName);
        }

        int pageSize=6;

        // Calculate the total number of pages based on the page size and total items
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        if(totalPages==0){
            totalPages=1;
        }
        // Ensure the requested page is within the valid range
        if (page < 1) {
            page = 1;
        } else if (page > totalPages) {
            page = totalPages;
        }

        // Calculate the starting index and ending index for the subset of items to display
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);





        List<Beverage> beverages =new ArrayList<>();
        if(id==0){
            beverages=beverageService.getSixItems(page,partialName);
        }else{
            beverages=beverageService.getSixItemsByCategoryId(id,page,partialName);
        }


        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("categories", categoryService.getAllData());
        model.addAttribute("beverages", beverages.stream().map(beverage -> Beverage.builder()
                .id(beverage.getId())
                .name(beverage.getName())
                .price(beverage.getPrice())
                .category(beverage.getCategory())
                .imageBase64(getImageBase64(beverage.getImage()))
                .build())

        );
        return "menu";
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/beverage_img/";
        File file = new File(filePath + fileName);
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
