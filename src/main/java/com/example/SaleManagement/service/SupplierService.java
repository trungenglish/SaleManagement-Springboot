package com.example.SaleManagement.service;

import com.example.SaleManagement.model.Category;
import com.example.SaleManagement.model.Product;
import com.example.SaleManagement.model.Supplier;
import com.example.SaleManagement.repository.SupplierRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getListSupplier(){
        return supplierRepository.findAll();
    }

    public Supplier getSupplier(int idSupplier){
        return supplierRepository.findByIdSupplier(idSupplier);
    }

    public Supplier addSupplier(Supplier request){
        Supplier supplier = new Supplier();

        supplier.setName(request.getName());
        supplier.setPhone(request.getPhone());
        supplier.setAddress(request.getAddress());

        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(int idCate, Supplier request){
        Supplier supplier = getSupplier(idCate);

        supplier.setName(request.getName());
        supplier.setPhone(request.getPhone());
        supplier.setAddress(request.getAddress());

        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(int idCate){
        supplierRepository.deleteById(idCate);
    }

    public void generateExcel(HttpServletResponse response) throws Exception {
        // code to generate excel
        List<Supplier> suppliers = supplierRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Supplier Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Phone");
        row.createCell(3).setCellValue("Address");

        int dataRowIndex = 1;

        for(Supplier supplier: suppliers) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);

            dataRow.createCell(0).setCellValue(supplier.getIdSupplier());
            dataRow.createCell(1).setCellValue(supplier.getName());
            dataRow.createCell(3).setCellValue(supplier.getPhone());
            dataRow.createCell(3).setCellValue(supplier.getAddress());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
