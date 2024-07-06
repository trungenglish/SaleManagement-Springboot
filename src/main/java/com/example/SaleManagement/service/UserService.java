package com.example.SaleManagement.service;

import com.example.SaleManagement.model.User;
import com.example.SaleManagement.repository.UsersRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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

        User customer = new User();
        customer.setUsername(request.getUsername());
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        return usersRepository.save(customer);
    }
    public User updateUser(int userId, User request){
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

    public void generateExcel(HttpServletResponse response) throws Exception {
        // code to generate excel
        List<User> users = usersRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("User Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("UserName");
        row.createCell(2).setCellValue("Password");
        row.createCell(3).setCellValue("Name");
        row.createCell(4).setCellValue("Phone");
        row.createCell(5).setCellValue("Email");

        int dataRowIndex = 1;

        for(User user: users) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(user.getIdUser());
            dataRow.createCell(1).setCellValue(user.getUsername());
            dataRow.createCell(2).setCellValue(user.getPassword());
            dataRow.createCell(3).setCellValue(user.getName());
            dataRow.createCell(4).setCellValue(user.getPhone());
            dataRow.createCell(5).setCellValue(user.getEmail());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
