package com.beverage.beverage.Service;

import java.util.List;
import java.util.Optional;

import com.beverage.beverage.Repo.CategoryRepo;
import com.beverage.beverage.dto.CategoryDto;
import com.beverage.beverage.entity.Category;

public interface CategoryService {
    void saveData(CategoryDto categoryDto);

    List<Category> getAllData();

    void deleteData(Integer id);

    Optional<Category> getDataById(int id);

}
