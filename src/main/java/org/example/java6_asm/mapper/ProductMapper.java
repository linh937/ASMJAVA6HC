package org.example.java6_asm.mapper;

import org.example.java6_asm.DTO.ProductDTO;
import org.example.java6_asm.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDTO toProductDTO(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDTO(
                product.getProductId(),
                product.getProduct_name(),
                product.getProduct_img(),
                product.getProduct_describe(),
                product.getProduct_price(),
                product.getBranch() != null ? product.getBranch().getBrands_name() : "Không có",
                product.getType() != null ? product.getType().getTypes_name() : "Không có"
        );
    }

    // Phương thức ánh xạ từ danh sách Product sang danh sách ProductDTO
    public static List<ProductDTO> toProductDTOList(List<Product> products) {
        if (products == null) {
            return new ArrayList<>();
        }

        return products.stream()
                .map(ProductMapper::toProductDTO)
                .collect(Collectors.toList());
    }
}
