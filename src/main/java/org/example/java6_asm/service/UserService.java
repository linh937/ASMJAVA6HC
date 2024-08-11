package org.example.java6_asm.service;

import org.example.java6_asm.model.User;
import org.example.java6_asm.model.UserRole;
import org.example.java6_asm.repository.UserRepository;
import org.example.java6_asm.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserByUsername(String username) {
        return userRepository.findById(username);
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public Optional<User> authenticate(String username, String passwords) {
        Optional<User> user = getUserByUsername(username);
        if (user.isPresent() && user.get().getPasswords().equals(passwords)) {
            return user;
        }
        return Optional.empty();
    }
//
//    public List<Integer> getRolesIdByUsername(String username) {
//        List<UserRole> userRoles = userRoleRepository.findByUsername_Username(username);
//        return userRoles.stream()
//                .map(userRole -> userRole.getroleid().getId())
//                .collect(Collectors.toList());
//    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> userOpt = getUserByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPasswords().equals(oldPassword)) { // Compare with the current password
                user.setPasswords(newPassword); // Update the password
                saveUser(user);
                return true; // Password change successful
            }
        }
        return false; // Password change failed
    }
    public boolean registerUser(User user) {
        // Kiểm tra xem tên người dùng đã tồn tại chưa
        if (userRepository.existsByUsername(user.getUsername())) {
            return false; // Tên người dùng đã tồn tại
        }
        // Lưu người dùng mới vào cơ sở dữ liệu
        userRepository.save(user);
        return true;
    }
}

