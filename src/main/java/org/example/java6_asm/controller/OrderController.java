package org.example.java6_asm.controller;

import org.example.java6_asm.DTO.OrderDTO;
import org.example.java6_asm.model.Order;
import org.example.java6_asm.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ModelMapper modelMapper;

//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrders() {
//        List<Order> orders = orderService.getAllOrders();
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
//        Optional<Order> order = orderService.getOrderById(id);
//        return order.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//        Order createdOrder = orderService.saveOrder(order);
//        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order order) {
//        if (orderService.getOrderById(id).isPresent()) {
//            order.setOrders_id(id);
//            Order updatedOrder = orderService.saveOrder(order);
//            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
//        if (orderService.getOrderById(id).isPresent()) {
//            orderService.deleteOrder(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
@GetMapping
public ResponseEntity<List<OrderDTO>> getAllOrders() {
    List<OrderDTO> orders = orderService.getAllOrders().stream()
            .map(order -> modelMapper.map(order, OrderDTO.class))
            .collect(Collectors.toList());
    return new ResponseEntity<>(orders, HttpStatus.OK);
}

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(value -> ResponseEntity.ok(modelMapper.map(value, OrderDTO.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        // Validate the input data
        if (orderDTO.getOrders_address() == null || orderDTO.getOrders_address().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Return an error if address is missing
        }

        Order order = modelMapper.map(orderDTO, Order.class);
        Order createdOrder = orderService.saveOrder(order);
        OrderDTO createdOrderDTO = modelMapper.map(createdOrder, OrderDTO.class);
        return new ResponseEntity<>(createdOrderDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable int id, @RequestBody OrderDTO orderDTO) {
        if (orderService.getOrderById(id).isPresent()) {
            Order order = modelMapper.map(orderDTO, Order.class);
            order.setOrders_id(id);
            Order updatedOrder = orderService.saveOrder(order);
            OrderDTO updatedOrderDTO = modelMapper.map(updatedOrder, OrderDTO.class);
            return new ResponseEntity<>(updatedOrderDTO, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        if (orderService.getOrderById(id).isPresent()) {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
