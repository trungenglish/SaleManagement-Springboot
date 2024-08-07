package com.example.SaleManagement.controller;

import com.example.SaleManagement.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@Controller
@RequestMapping("/api")
public class PdfController {
    @Autowired
    private PdfService pdfService;

    @GetMapping("pdfPurchaseOrder/{purchaseOrderId}")
    public ResponseEntity<InputStreamResource> downloadPdfPurchaseOrder(@PathVariable int purchaseOrderId) {
        ByteArrayInputStream pdf = pdfService.createPdfPurchaseOrder(purchaseOrderId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=purchase_order.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

    @GetMapping("pdfOrder/{orderId}")
    public ResponseEntity<InputStreamResource> downloadPdfOrder(@PathVariable int orderId) {
        ByteArrayInputStream pdf = pdfService.createPdfOrder(orderId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Invoice.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }


//    @GetMapping ("/createPdf")
//    public ResponseEntity<InputStreamResource> createPdf() {
//
//        ByteArrayInputStream pdf = pdfService.createPdf();
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Content-Disposition", "inline; filename=PurchaseOrder.pdf");
//
//        return ResponseEntity
//                .ok()
//                .headers(httpHeaders)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(pdf));
//    }

}
