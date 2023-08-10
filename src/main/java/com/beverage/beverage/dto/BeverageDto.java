package com.beverage.beverage.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BeverageDto {
    private int id;
    private String name;
    private String description;
    private double price;
    private MultipartFile image;
    private int categoryId;
}