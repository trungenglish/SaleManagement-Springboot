package com.example.SaleManagement.repository;

import com.example.SaleManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer>{
    long count();
    boolean existsByUsername(String username);// method này kiểm tra xem username đã tồn tại chưa
    User findById(int id); // method này tìm theo id
}
