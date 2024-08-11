package org.example.java6_asm.service;


import org.example.java6_asm.model.OrderDetail;
import org.example.java6_asm.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }


    public Optional<OrderDetail> getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id);
    }


    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }


    public void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }


}
