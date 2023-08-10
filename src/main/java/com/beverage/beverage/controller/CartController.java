package com.beverage.beverage.controller;

import com.beverage.beverage.Service.CartService;
import com.beverage.beverage.Service.UserService;
import com.beverage.beverage.dto.CartDto;
import com.beverage.beverage.entity.Cart;
import com.beverage.beverage.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @PostMapping("/add")
    public String addToCart(@Valid CartDto cartDto) {
        System.out.println(cartDto.getBeverageId());
        cartService.addToCart(cartDto);
        return "redirect:/dashboard/list";
    }
    @GetMapping("/list")
    public String getCartList(Model model) {
        User user = userService.getActiveUser().get();
        List<Cart> carts=cartService.getCartListByStatusUnpaid(user);
        model.addAttribute("carts", carts);
        model.addAttribute("total", carts.stream().mapToDouble(Cart::getTotalPrice).sum());
        model.addAttribute("user", user);
        return "cart";
    }

    @PostMapping("/delete/{id}")
    public String deleteCart(@PathVariable int id) {
        cartService.deleteCart(id);
        return "redirect:/cart/list";
    }

    @PostMapping("/edit")
    public String editCart(@Valid CartDto cartDto) {
        try{
            cartService.editCart(cartDto);
        }
        catch (AssertionError e){
            return "redirect:/cart/list?invalidQuantity";
        }
        return "redirect:/cart/list";
    }
}
