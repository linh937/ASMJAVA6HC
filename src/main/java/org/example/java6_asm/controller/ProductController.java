package org.example.java6_asm.controller;

import org.example.java6_asm.DTO.ProductDTO;
import org.example.java6_asm.mapper.ProductMapper;
import org.example.java6_asm.model.Branch;
import org.example.java6_asm.model.Inventory;
import org.example.java6_asm.model.Product;
import org.example.java6_asm.model.ProductType;
import org.example.java6_asm.repository.BranchRepository;
import org.example.java6_asm.repository.ProductTypeRepository;
import org.example.java6_asm.service.BranchService;
import org.example.java6_asm.service.ProductService;
import org.example.java6_asm.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173") // Cho phép các yêu cầu từ địa chỉ này
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BranchService branchService;

    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    public ResponseEntity<List<Product>> getAllProducts() {
//        List<Product> products = productService.getAllProducts();
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
@GetMapping
public ResponseEntity<List<ProductDTO>> getAllProducts() {
    List<Product> products = productService.getAllProducts();
    List<ProductDTO> productDTOs = ProductMapper.toProductDTOList(products);
    return new ResponseEntity<>(productDTOs, HttpStatus.OK);
}


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            Optional<Inventory> inventory = productService.getInventoryByProductId(id);
            Product productWithInventory = product.get();
            if (inventory.isPresent()) {
                System.out.println(" productWithInventory.setInventories(inventorys.get());");
                // Cần thêm phương thức setInventory vào Product nếu cần
            }
            return ResponseEntity.ok(productWithInventory);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

//    @PostMapping
//    public ResponseEntity<Product> createProduct(
//            @RequestParam("product_name") String productName,
//            @RequestParam("product_describe") String productDescribe,
//            @RequestParam("product_price") double productPrice,
//            @RequestParam("branch_id") int branchId,
//            @RequestParam("type_id") int typeId,
//            @RequestParam(value = "product_img", required = false) MultipartFile productImg) {
//
//        Optional<Branch> branchOpt = branchService.getBranchById(branchId);
//        Optional<ProductType> typeOpt = productTypeService.getProductTypeById(typeId);
//
//        if (!branchOpt.isPresent() || !typeOpt.isPresent()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        Product newProduct = new Product();
//        newProduct.setProduct_name(productName);
//        newProduct.setProduct_describe(productDescribe);
//        newProduct.setProduct_price(productPrice);
//        newProduct.setBranch(branchOpt.get());
//        newProduct.setType(typeOpt.get());
//
//        if (productImg != null && !productImg.isEmpty()) {
//            try {
//                byte[] imageBytes = productImg.getBytes();
//                String imgData = Base64.getEncoder().encodeToString(imageBytes);
//                newProduct.setProduct_img(imgData);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//            }
//        }
//
//        Product savedProduct = productService.saveProduct(newProduct);
//        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//    }
@PostMapping
public ResponseEntity<Product> createProduct(
        @RequestParam("product_name") String productName,
        @RequestParam("product_describe") String productDescribe,
        @RequestParam("product_price") double productPrice,
        @RequestParam("branch_id") int branchId,
        @RequestParam("type_id") int typeId,
        @RequestParam(value = "product_img", required = false) MultipartFile productImg) {

    Optional<Branch> branchOpt = branchService.getBranchById(branchId);
    Optional<ProductType> typeOpt = productTypeService.getProductTypeById(typeId);

    if (!branchOpt.isPresent() || !typeOpt.isPresent()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    Product newProduct = new Product();
    newProduct.setProduct_name(productName);
    newProduct.setProduct_describe(productDescribe);
    newProduct.setProduct_price(productPrice);
    newProduct.setBranch(branchOpt.get());
    newProduct.setType(typeOpt.get());

    if (productImg != null && !productImg.isEmpty()) {
        try {
            byte[] imageBytes = productImg.getBytes();
            String imgData = Base64.getEncoder().encodeToString(imageBytes);
            newProduct.setProduct_img(imgData);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    Product savedProduct = productService.saveProduct(newProduct);
    return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
}


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") int id,
            @RequestParam("product_name") String productName,
            @RequestParam("product_describe") String productDescribe,
            @RequestParam("product_price") double productPrice,
            @RequestParam(value = "product_img", required = false) MultipartFile productImg) {
        // Kiểm tra xem sản phẩm có tồn tại không
        Optional<Product> existingProductOpt = productService.getProductById(id);
        if (!existingProductOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Cập nhật các thông tin của sản phẩm
        Product existingProduct = existingProductOpt.get();
        existingProduct.setProduct_name(productName);
        existingProduct.setProduct_describe(productDescribe);
        existingProduct.setProduct_price(productPrice);

        // Xử lý dữ liệu ảnh nếu có
        if (productImg != null && !productImg.isEmpty()) {
            try {
                byte[] imageBytes = productImg.getBytes();
                String imgData = Base64.getEncoder().encodeToString(imageBytes);
                existingProduct.setProduct_img(imgData);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }

        // Lưu sản phẩm đã được cập nhật
        Product updatedProduct = productService.saveProduct(existingProduct);

        return ResponseEntity.ok(updatedProduct);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
        if (productService.getProductById(id).isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
