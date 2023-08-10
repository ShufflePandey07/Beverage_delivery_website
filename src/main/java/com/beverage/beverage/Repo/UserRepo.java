package com.beverage.beverage.Repo;

import com.beverage.beverage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    @Query(value = "select * from users where user_email = ?1",nativeQuery = true)
    Optional<User> findByEmail(String email);
}
