package com.example.SaleManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderItemDto {
    private int productId;
    private int quantityItemOrderPurchase;
    private double priceOrderPurchase;
}
