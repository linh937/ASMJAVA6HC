package org.example.java6_asm.controller;

import org.example.java6_asm.model.ProductType;
import org.example.java6_asm.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/types")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        return new ResponseEntity<>(productTypes, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProductType> createProductType(@RequestBody ProductType productType) {
        ProductType createdProductType = productTypeService.createProductType(productType);
        return new ResponseEntity<>(createdProductType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductType> updateProductType(@PathVariable int id, @RequestBody ProductType productType) {
        Optional<ProductType> existingProductType = productTypeService.getProductTypeById(id);
        if (existingProductType.isPresent()) {
            ProductType updatedProductType = productTypeService.updateProductType(id, productType);
            return new ResponseEntity<>(updatedProductType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductType(@PathVariable int id) {
        if (productTypeService.deleteProductType(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
