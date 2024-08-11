package org.example.java6_asm.DTO;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long orders_id; // Renamed from ordersId
    private String fullname;
    private String email;
    private String phone;
    private String orders_address; // Matches the column name in the DB
    private Date orders_time; // Updated field name
    private List<OrderItemDTO> items;
    private double totalAmount;

    // Getters and Setters
    public Long getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(Long orders_id) {
        this.orders_id = orders_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrders_address() {
        return orders_address;
    }

    public void setOrders_address(String orders_address) {
        this.orders_address = orders_address;
    }

    public Date getOrders_time() {
        return orders_time;
    }

    public void setOrders_time(Date orders_time) {
        this.orders_time = orders_time;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orders_id=" + orders_id + // Updated field name in toString
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", orders_address='" + orders_address + '\'' +
                ", orders_time=" + orders_time + // Updated field name in toString
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
