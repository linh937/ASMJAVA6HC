package org.example.java6_asm.controller;


import org.example.java6_asm.DTO.CartItemDTO;
import org.example.java6_asm.model.Cart;
import org.example.java6_asm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable int id) {
        Optional<Cart> cart = cartService.getCartById(id);
        return cart.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart createdCart = cartService.saveCart(cart);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody Cart cart) {
        if (cartService.getCartById(id).isPresent()) {
            cart.setCart_id(id);
            Cart updatedCart = cartService.saveCart(cart);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable int id) {
        if (cartService.getCartById(id).isPresent()) {
            cartService.deleteCart(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//    @PostMapping
//    public ResponseEntity<Void> addToCart(@RequestBody CartItemDTO cartItemDTO) {
//        try {
//            cartService.addToCart(cartItemDTO.getProductId());
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
