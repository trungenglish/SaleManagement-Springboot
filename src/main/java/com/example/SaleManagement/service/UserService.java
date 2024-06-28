package com.example.SaleManagement.service;

import com.example.SaleManagement.model.User;
import com.example.SaleManagement.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> getListUser(){
        return usersRepository.findAll();
    }

    public boolean existsByUsername(String username) {
        return usersRepository.existsByUsername(username);
    }

    public User addUser (User request){

//        if (usersRepository.existsByUsername(request.getUsername())){
//            throw new RuntimeException("User existed");
//        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        User user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return usersRepository.save(user);
    }
    public User updateUser(int userId,User request){
        User user = getUser(userId);

        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setName(request.getName());

        return usersRepository.save(user);
    }

    public User getUser(int id){
        return usersRepository.findById(id);
    }

    public void deleteUser(int id){
        User user = getUser(id);
        usersRepository.delete(user);
    }
}
