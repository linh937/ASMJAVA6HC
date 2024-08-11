package org.example.java6_asm.service;

import org.example.java6_asm.model.Inventory;
import org.example.java6_asm.model.Product;
import org.example.java6_asm.repository.InventoryRepository;
import org.example.java6_asm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryRepository inventoryRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int productId) {
        return productRepository.findById(productId);
    }
    public Optional<Inventory> getInventoryByProductId(int productId) {
        return inventoryRepository.findByProduct_ProductId(productId);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new RuntimeException("Product with id " + productId + " does not exist.");
        }
    }



}
