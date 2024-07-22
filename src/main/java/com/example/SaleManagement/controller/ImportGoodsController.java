package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.PurchaseOrder;
import com.example.SaleManagement.service.ImportGoodsService;
import com.example.SaleManagement.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/import-goods")
public class ImportGoodsController {
    @Autowired
    private ImportGoodsService importGoodsService;

    @GetMapping("/list")
    public String listImportGoods(Model model) {
        model.addAttribute("importGoods", importGoodsService.getListImportGoods());
        return "layouts/importGoods/layout_import_goods";
    }

    @GetMapping("/showDetail")
    public String detailPurchaseOrder(@RequestParam("purchaseOrderId") int id, Model model) {
        model.addAttribute("purchaseOrder", importGoodsService.getPurchaseOrder(id));
        return "layouts/importGoods/layout_detail_import_goods";
    }
}
