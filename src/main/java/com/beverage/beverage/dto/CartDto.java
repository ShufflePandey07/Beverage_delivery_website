package com.beverage.beverage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {
    private int id;
    private int cartQty;
    private int beverageId;
    private int userId;
    private double totalPrice;
}

