package com.example.SaleManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PurchaseOrderDto {
    private int idSupplier;
    private List<PurchaseOrderItemDto> items;
}
