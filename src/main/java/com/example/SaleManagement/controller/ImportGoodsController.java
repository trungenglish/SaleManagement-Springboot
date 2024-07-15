package com.example.SaleManagement.controller;

import com.example.SaleManagement.service.ImportGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
