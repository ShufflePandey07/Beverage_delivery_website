package com.beverage.beverage.Service.impl;

import com.beverage.beverage.Repo.CartRepo;
import com.beverage.beverage.Service.BeverageService;
import com.beverage.beverage.Service.CartService;
import com.beverage.beverage.Service.UserService;
import com.beverage.beverage.dto.CartDto;
import com.beverage.beverage.entity.Beverage;
import com.beverage.beverage.entity.Cart;
import com.beverage.beverage.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepo;
    private final BeverageService beverageService;
    private final UserService userService;
    @Override
    public void addToCart(CartDto cartDto) {
        Cart cart = cartRepo.findById(cartDto.getId()).orElse(new Cart());
        cart.setCartQty(cartDto.getCartQty());
        cart.setTotalPrice(cartDto.getTotalPrice());

        Optional<Beverage> optionalBeverage = beverageService.getItemById(cartDto.getBeverageId());

        if (optionalBeverage.isPresent()) {
            Beverage item = optionalBeverage.get();
            cart.setBeverage(item);
        }

        Optional<User> optionalUser = userService.getActiveUser();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            cart.setUser(user);
        }
        cart.setStatus("Pending");
        cartRepo.save(cart);
    }

    @Override
    public List<Cart> getCartListByStatusUnpaid(User user) {
        return cartRepo.findAllByUserAndStatusUnpaid(user.getId());
    }

    @Override
    public void deleteCart(int id) {
        cartRepo.deleteById(id);
    }

    @Override
    public void editCart(CartDto cartDto) {
        Cart cart=cartRepo.findById(cartDto.getId()).get();
        cart.setCartQty(cartDto.getCartQty());
        double price=cart.getBeverage().getPrice();
        cart.setTotalPrice(price*cartDto.getCartQty());
        cartRepo.save(cart);

    }
}
