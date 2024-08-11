//package org.example.java6_asm.controller;
//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletResponse;
//import org.example.java6_asm.model.User;
//import org.example.java6_asm.service.TokenService;
//import org.example.java6_asm.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.security.core.Authentication;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private TokenService tokenService; // Token service for JWT
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//
//    @GetMapping("/{username}")
//    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
//        Optional<User> user = userService.getUserByUsername(username);
//        return user.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
////    @PostMapping
////    public ResponseEntity<User> createUser(@RequestBody User user) {
////        User createdUser = userService.saveUser(user);
////        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
////    }
//
//    @PutMapping("/{username}")
//    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user) {
//        if (userService.getUserByUsername(username).isPresent()) {
//            user.setUsername(username);
//            User updatedUser = userService.saveUser(user);
//            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{username}")
//    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
//        if (userService.getUserByUsername(username).isPresent()) {
//            userService.deleteUser(username);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    //    @PostMapping("/Login")
////    public ResponseEntity<String> login(@RequestBody User loginRequest) {
////        Optional<User> user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPasswords());
////        if (user.isPresent()) {
////            String token = tokenService.generateToken(user.get());
////            return ResponseEntity.ok(token);
////        } else {
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
////        }
////    }
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User loginRequest, HttpServletResponse response) {
//        Optional<User> user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPasswords());
//        if (user.isPresent()) {
//            String token = tokenService.generateToken(user.get());
//
//            // Tạo và thiết lập cookie
//            Cookie cookie = new Cookie("authToken", token);
//            cookie.setHttpOnly(true); // Bảo vệ cookie khỏi truy cập bằng JavaScript
//            cookie.setSecure(true); // Chỉ gửi cookie qua HTTPS
//            cookie.setPath("/"); // Cookie có thể được truy cập từ bất kỳ đường dẫn nào trong ứng dụng
//            cookie.setMaxAge(60 * 60); // Cookie hết hạn sau 1 giờ
//            response.addCookie(cookie);
//
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
////    @PostMapping("/register")
////    public ResponseEntity<?> registerUser(@RequestBody User user) {
////        try {
////            User registeredUser = userService.registerUser(user);
////            return ResponseEntity.ok(registeredUser);
////        } catch (Exception e) {
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USERNAME_TAKEN");
////        }
////    }
//}
//
//
//
//
package org.example.java6_asm.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.example.java6_asm.DTO.PasswordChangeRequest;
import org.example.java6_asm.DTO.UserDTO;
import org.example.java6_asm.model.User;
import org.example.java6_asm.service.TokenService;
import org.example.java6_asm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173") // Thay đổi URL theo địa chỉ frontend của bạn
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService; // Token service for JWT

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            // Log lỗi và trả về lỗi máy chủ nội bộ
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @GetMapping("/{username}")
//    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
//        try {
//            Optional<User> user = userService.getUserByUsername(username);
//            return user.map(ResponseEntity::ok)
//                    .orElseGet(() -> ResponseEntity.notFound().build());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//

    //    @PutMapping("/{username}")
//    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user) {
//        try {
//            Optional<User> existingUserOpt = userService.getUserByUsername(username);
//            if (existingUserOpt.isPresent()) {
//                User existingUser = existingUserOpt.get();
//
//                // Chỉ cập nhật các trường thông tin người dùng mà không làm thay đổi các liên kết khác
//                existingUser.setEmail(user.getEmail());
//                existingUser.setFullname(user.getFullname());
//                existingUser.setPhone(user.getPhone());
//
//                // Lưu thông tin người dùng đã cập nhật
//                User updatedUser = userService.saveUser(existingUser);
//                return ResponseEntity.ok(updatedUser);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (Exception e) {
//            e.printStackTrace(); // In lỗi để hỗ trợ gỡ lỗi
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        Optional<User> userOpt = userService.getUserByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserDTO userDTO = new UserDTO(
                    user.getUsername(),
                    user.getFullname(),
                    user.getEmail(),
                    user.getPhone()


            );
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
        Optional<User> existingUserOpt = userService.getUserByUsername(username);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Cập nhật các trường thông tin theo yêu cầu
            if (userDTO.getEmail() != null) {
                existingUser.setEmail(userDTO.getEmail());
            }
            if (userDTO.getFullname() != null) {
                existingUser.setFullname(userDTO.getFullname());
            }
            if (userDTO.getPhone() != null) {
                existingUser.setPhone(userDTO.getPhone());
            }

            // Lưu thông tin người dùng đã cập nhật
            User updatedUser = userService.saveUser(existingUser);
            UserDTO updatedUserDTO = new UserDTO(
                    updatedUser.getUsername(),
                    updatedUser.getFullname(),
                    updatedUser.getEmail(),
                    updatedUser.getPhone()
            );
            return ResponseEntity.ok(updatedUserDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        try {
            if (userService.getUserByUsername(username).isPresent()) {
                userService.deleteUser(username);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest, HttpServletResponse response) {
        try {
            Optional<User> user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPasswords());
            if (user.isPresent()) {
                String token = tokenService.generateToken(user.get());

                // Tạo và thiết lập cookie
                Cookie cookie = new Cookie("authToken", token);
                cookie.setHttpOnly(true); // Bảo vệ cookie khỏi truy cập bằng JavaScript
                cookie.setSecure(true); // Chỉ gửi cookie qua HTTPS
                cookie.setPath("/"); // Cookie có thể được truy cập từ bất kỳ đường dẫn nào trong ứng dụng
                cookie.setMaxAge(60 * 60); // Cookie hết hạn sau 1 giờ
                response.addCookie(cookie);

                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/{username}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable String username,
                                                 @RequestParam String oldPassword,
                                                 @RequestParam String newPassword) {
        try {
            boolean isPasswordChanged = userService.changePassword(username, oldPassword, newPassword);
            if (isPasswordChanged) {
                return ResponseEntity.ok("Password changed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Old password is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while changing the password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            boolean isRegistered = userService.registerUser(user);
            if (isRegistered) {
                return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Username already exists.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Registration failed. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}