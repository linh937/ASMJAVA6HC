package org.example.java6_asm.controller;


import org.example.java6_asm.DTO.OrderItemDTO;
import org.example.java6_asm.model.OrderDetail;
import org.example.java6_asm.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable Long id) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetailById(id);
        return orderDetail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
//    @GetMapping("/order-details")
//    public ResponseEntity<List<OrderItemDTO>> getOrderDetailsByOrderId(@RequestParam Long orderId) {
//        List<OrderItemDTO> orderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
//        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
//    }


    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        OrderDetail createdOrderDetail = orderDetailService.saveOrderDetail(orderDetail);
        return new ResponseEntity<>(createdOrderDetail, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        if (orderDetailService.getOrderDetailById(id).isPresent()) {
            orderDetail.setDetailorder_id(id);
            OrderDetail updatedOrderDetail = orderDetailService.saveOrderDetail(orderDetail);
            return new ResponseEntity<>(updatedOrderDetail, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id) {
        if (orderDetailService.getOrderDetailById(id).isPresent()) {
            orderDetailService.deleteOrderDetail(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
