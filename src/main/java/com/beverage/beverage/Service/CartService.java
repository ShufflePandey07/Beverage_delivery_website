package com.beverage.beverage.Service;

import com.beverage.beverage.dto.CartDto;
import com.beverage.beverage.entity.Cart;
import com.beverage.beverage.entity.User;

import java.util.List;


public interface CartService {
    void addToCart(CartDto cartDto);

    List<Cart> getCartListByStatusUnpaid(User user);

    void deleteCart(int id);

    void editCart(CartDto cartDto);
}
