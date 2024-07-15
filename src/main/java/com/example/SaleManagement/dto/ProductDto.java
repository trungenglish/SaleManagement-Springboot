package com.example.SaleManagement.dto;

import com.example.SaleManagement.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {// chứa dữ liệu từ form gửi xuống tránh việc xác thực imageFile của Spring
    private  int idPro;
    private String namePro;
    private double price;
    private int quantity;
    private Category category;
    private String description;
}
