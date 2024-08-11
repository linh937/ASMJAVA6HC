package org.example.java6_asm.DTO;

public class UserDTO {
    private String username;
    private String fullname;
    private String email;
    private String phone;

    // Constructors
    public UserDTO() {}

    public UserDTO(String username, String fullname, String email, String phone) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
