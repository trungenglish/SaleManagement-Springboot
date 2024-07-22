package com.example.SaleManagement.controller;

import com.example.SaleManagement.dto.ProductDto;
import com.example.SaleManagement.model.Product;
import com.example.SaleManagement.service.CategoryService;
import com.example.SaleManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

import java.sql.SQLException;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("proId") int id) throws IOException, SQLException
    {
        Product product = productService.getProduct(id);
        byte [] imageBytes = null;
        imageBytes = product.getImageFile().getBytes(1,(int) product.getImageFile().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("products", productService.getListProduct());
        model.addAttribute("categories",categoryService.getListCategory());
        model.addAttribute("product", new Product());
        return "layouts/product/layout_list_product";
    }

    @GetMapping("/formUpdate")
    public String showFormUpdate(@RequestParam("proId") int id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        model.addAttribute("categories",categoryService.getListCategory());
        return "layouts/product/layout_update_product";
    }


    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") ProductDto productDTO, @RequestParam("imageFile") MultipartFile file)throws IOException, SerialException, SQLException {
        productService.addProduct(productDTO, file);
        return "redirect:/products/list";
    }

    @GetMapping("/showDetail")
    public String detailProduct(@RequestParam("proId") int id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "layouts/product/layout_detail_product";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("product") ProductDto productDTO, @RequestParam("imageFile") MultipartFile file) throws IOException, SerialException, SQLException {
        productService.updateProduct(productDTO.getIdPro(),productDTO,file);
        return "redirect:/products/list";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("idPro") int id){
        productService.deleteProduct(id);
        return "redirect:/products/list";
    }
}
