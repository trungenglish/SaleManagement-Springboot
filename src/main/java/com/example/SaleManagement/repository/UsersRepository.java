package com.example.SaleManagement.repository;

import com.example.SaleManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer>{
    public User findByUsername(String username);// method này tìm theo trong entity User có thuộc tính username
}
