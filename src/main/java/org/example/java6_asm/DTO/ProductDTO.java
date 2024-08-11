package org.example.java6_asm.DTO;

public class ProductDTO {
    private int productId;
    private String product_name;
    private String product_img;
    private String product_describe;
    private double product_price;
    private String branchName; // Tên chi nhánh
    private String typeName;   // Tên loại sản phẩm

    // Constructor mặc định
    public ProductDTO() {
    }

    // Constructor với tất cả các thuộc tính
    public ProductDTO(int productId, String product_name, String product_img,
                      String product_describe, double product_price,
                      String branchName, String typeName) {
        this.productId = productId;
        this.product_name = product_name;
        this.product_img = product_img;
        this.product_describe = product_describe;
        this.product_price = product_price;
        this.branchName = branchName;
        this.typeName = typeName;
    }


    // Getter và Setter cho tất cả các thuộc tính
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public String getProduct_describe() {
        return product_describe;
    }

    public void setProduct_describe(String product_describe) {
        this.product_describe = product_describe;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", product_name='" + product_name + '\'' +
                ", product_img='" + product_img + '\'' +
                ", product_describe='" + product_describe + '\'' +
                ", product_price=" + product_price +
                ", branchName='" + branchName + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }

}
