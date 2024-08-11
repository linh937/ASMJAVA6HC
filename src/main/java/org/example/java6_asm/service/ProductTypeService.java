package org.example.java6_asm.service;

import org.example.java6_asm.model.ProductType;
import org.example.java6_asm.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    public Optional<ProductType> getProductTypeById(int id) {
        return productTypeRepository.findById(id);
    }


    public ProductType saveProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

//    public void deleteProductType(int id) {
//        productTypeRepository.deleteById(id);
//    }
//    public ProductType createProductType(ProductType productType) {
//        return productTypeRepository.save(productType);
//    }
public ProductType createProductType(ProductType productType) {
    return productTypeRepository.save(productType);
}

    /**
     * Update an existing product type.
     *
     * @param id - The ID of the product type to update.
     * @param productType - The updated product type data.
     * @return ProductType - The updated product type.
     */
    public ProductType updateProductType(int id, ProductType productType) {
        if (productTypeRepository.existsById(id)) {
            productType.setTypes_id(id); // Set the ID to ensure it's updated
            return productTypeRepository.save(productType);
        } else {
            return null; // Or throw an exception if preferred
        }
    }

    /**
     * Delete a product type by its ID.
     *
     * @param id - The ID of the product type to delete.
     * @return boolean - True if the product type was deleted, false otherwise.
     */
    public boolean deleteProductType(int id) {
        if (productTypeRepository.existsById(id)) {
            productTypeRepository.deleteById(id);
            return true;
        } else {
            return false; // Or throw an exception if preferred
        }
    }
}
