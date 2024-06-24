package com.example.SaleManagement.service;

import com.example.SaleManagement.model.User;
import com.example.SaleManagement.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> getListUser(){
        return usersRepository.findAll();
    }
}
