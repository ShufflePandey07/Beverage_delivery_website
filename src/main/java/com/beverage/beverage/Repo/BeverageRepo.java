package com.beverage.beverage.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beverage.beverage.entity.Beverage;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BeverageRepo extends JpaRepository<Beverage, Integer> {

    @Query(value = "select * from beverage where category_id = ?1 and LOWER(beverage_name) LIKE CONCAT('%', LOWER(?3), '%') order by id offset ?2 limit 6", nativeQuery = true)
    List<Beverage> findSixItemsByCategoryId(int id, int offset, String partialName);

    @Query(value = "select * from beverage where LOWER(beverage_name) LIKE CONCAT('%', LOWER(?2), '%') order by id  offset ?1 limit 6", nativeQuery = true)
    List<Beverage> findSixItems(int offset, String partialName);

    @Query(value = "select count(*) from beverage where LOWER(beverage_name) LIKE CONCAT('%', LOWER(?1), '%')", nativeQuery = true)
    int countAllItems(String partialName);

    @Query(value = "select count(*) from beverage where category_id = ?1 and LOWER(beverage_name) LIKE CONCAT('%', LOWER(?2), '%')", nativeQuery = true)
    int countAllByCategoryId(int id, String partialName);

    @Query(value = "select * from beverage order by id desc limit 3", nativeQuery = true)
    List<Beverage> findLatestThreeData();
}
