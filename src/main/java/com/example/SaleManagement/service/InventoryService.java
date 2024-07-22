package com.example.SaleManagement.service;

import com.example.SaleManagement.model.Inventory;
import com.example.SaleManagement.model.Product;
import com.example.SaleManagement.repository.InventoriesRepository;
import com.example.SaleManagement.repository.ProductsRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoriesRepository inventoriesRepository;
    @Autowired
    private ProductsRepository productsRepository;

    public Inventory getInventory(int id){
        return inventoriesRepository.findByIdInventory(id);
    }

    public List<Inventory> getListInventory(){
        return inventoriesRepository.findAll();
    }

    @Transactional
    public void updateQuantity(Inventory request, int id) {
        Inventory inventory = getInventory(id);

        inventory.setQuantity(request.getQuantity());
        inventory.setLastUpdated(LocalDateTime.now());

        inventoriesRepository.save(inventory);
    }
}


