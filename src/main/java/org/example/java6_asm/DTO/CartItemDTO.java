package org.example.java6_asm.DTO;

public class CartItemDTO {
    private Long productId;
    private Integer quantity;

    public CartItemDTO() {
        // Constructor mặc định
    }

    public CartItemDTO(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemDTO{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
