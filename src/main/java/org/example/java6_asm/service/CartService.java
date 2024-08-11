package org.example.java6_asm.service;

import org.example.java6_asm.model.Cart;
import org.example.java6_asm.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;


    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }


    public Optional<Cart> getCartById(int id) {
        return cartRepository.findById(id);
    }


    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }


    public void deleteCart(int id) {
        cartRepository.deleteById(id);
    }
}
