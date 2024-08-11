package org.example.java6_asm.DTO;

public class InventoryDTO {
    private int id;
    private String productName;
    private Integer quantity;

    // Default constructor
    public InventoryDTO() {
    }

    // Parameterized constructor
    public InventoryDTO(int id, String productName, Integer quantity) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "InventoryDTO{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
