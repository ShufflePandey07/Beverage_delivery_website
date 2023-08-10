package com.beverage.beverage.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beverage.beverage.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
