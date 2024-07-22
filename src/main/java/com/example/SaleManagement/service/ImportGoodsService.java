package com.example.SaleManagement.service;

import com.example.SaleManagement.model.PurchaseOrder;
import com.example.SaleManagement.repository.ImportGoodsRepository;
import com.example.SaleManagement.repository.PurchaseOrderRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportGoodsService {
    @Autowired
    private ImportGoodsRepository importGoodsRepository;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrder getPurchaseOrder(int id){
        return purchaseOrderRepository.findByIdPurchaseOrder(id);
    }

    public List<PurchaseOrder> getListImportGoods(){
        return importGoodsRepository.findAll();
    }



    public void generateExcel(HttpServletResponse response) throws Exception {
        // code to generate excel
        List<PurchaseOrder> purchaseOrders = importGoodsRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Purchase Order Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Suplier");
        row.createCell(2).setCellValue("Date");
        row.createCell(3).setCellValue("Total");

        int dataRowIndex = 1;

        for(PurchaseOrder purchaseOrder: purchaseOrders) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(purchaseOrder.getIdPurchaseOrder());
            dataRow.createCell(1).setCellValue(purchaseOrder.getSupplier().getName());
            dataRow.createCell(2).setCellValue(purchaseOrder.getDateOrderPurchase());
            dataRow.createCell(3).setCellValue(purchaseOrder.getTotalPurchase());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
