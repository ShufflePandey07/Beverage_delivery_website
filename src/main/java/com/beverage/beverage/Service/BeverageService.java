package com.beverage.beverage.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.beverage.beverage.dto.BeverageDto;
import com.beverage.beverage.entity.Beverage;

public interface BeverageService {

    void saveData(BeverageDto beverageDto) throws IOException;

    List<Beverage> getAllData();

    void deleteBeverage(int id);

    int countAllItems(String partialName);

    int countAllItemsByCategoryId(int id, String partialName);

    List<Beverage> getSixItems(int page, String partialName);

    List<Beverage> getSixItemsByCategoryId(int id, int page, String partialName);

    Optional<Beverage> getItemById(int id);

    List<Beverage> getLatestThreeData();
}
