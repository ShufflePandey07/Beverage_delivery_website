package com.beverage.beverage.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.beverage.beverage.Repo.CategoryRepo;
import com.beverage.beverage.Service.CategoryService;
import com.beverage.beverage.dto.CategoryDto;
import com.beverage.beverage.entity.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public void saveData(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());

        categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllData() {
        return categoryRepo.findAll();
    }

    @Override
    public void deleteData(Integer id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Optional<Category> getDataById(int id) {
        // TODO Auto-generated method stub
        return categoryRepo.findById(id);
    }

}
