package com.example.SaleManagement.service;

import com.example.SaleManagement.dto.ProductDto;
import com.example.SaleManagement.model.Inventory;
import com.example.SaleManagement.model.Product;
import com.example.SaleManagement.repository.InventoriesRepository;
import com.example.SaleManagement.repository.ProductsRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private InventoriesRepository inventoryRepository;
    @Autowired
    private ProductsRepository productsRepository;

    public List<Product> getListProduct(){
        return productsRepository.findAll();
    }

    public Product getProduct(int id){
        return productsRepository.findByIdPro(id);
    }

    public long countProducts(){
        return productsRepository.count();
    }

    @Transactional
    public void addProduct(ProductDto productDTO, MultipartFile file) throws IOException, SerialException, SQLException {
        Product product = new Product();
        product.setNamePro(productDTO.getNamePro());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setDescription(productDTO.getDescription());

        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);
        product.setImageFile(blob);

        // Lưu sản phẩm vào cơ sở dữ liệu trước
        Product savedProduct = productsRepository.save(product);

        // Tạo một đối tượng Inventory mới cho sản phẩm vừa lưu
        Inventory inventory = new Inventory();
        inventory.setProduct(savedProduct); // Đảm bảo Inventory tham chiếu tới Product vừa lưu
        inventory.setQuantity(0); // Số lượng ban đầu của sản phẩm mới
        inventory.setLastUpdated(LocalDateTime.now());

        // Lưu Inventory vào cơ sở dữ liệu
        inventoryRepository.save(inventory);
    }


    public void updateProduct(int id, ProductDto request, MultipartFile file) throws IOException, SerialException, SQLException {
        Product product = getProduct(id);

        product.setNamePro(request.getNamePro());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setDescription(request.getDescription());
        if (file != null && !file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Blob blob = new SerialBlob(bytes);
            product.setImageFile(blob);
        }
        productsRepository.save(product);
    }

    public void deleteProduct(int id){
        productsRepository.deleteById(id);
    }

    public void generateExcel(HttpServletResponse response) throws Exception {
        // code to generate excel
        List<Product> products = productsRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Product Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Category");
        row.createCell(3).setCellValue("Price");
        row.createCell(4).setCellValue("Description");

        int dataRowIndex = 1;

        for(Product product: products) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(product.getIdPro());
            dataRow.createCell(1).setCellValue(product.getNamePro());
            dataRow.createCell(2).setCellValue(product.getCategory().getName());
            dataRow.createCell(3).setCellValue(product.getPrice());
            dataRow.createCell(4).setCellValue(product.getDescription());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
