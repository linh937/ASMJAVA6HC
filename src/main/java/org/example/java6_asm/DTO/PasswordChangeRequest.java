package org.example.java6_asm.DTO;

public class PasswordChangeRequest {
    private String oldPassword;
    private String newPassword;

    // Constructor không tham số
    public PasswordChangeRequest() {}

    // Constructor với tất cả các trường
    public PasswordChangeRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    // Getter và Setter cho oldPassword
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    // Getter và Setter cho newPassword
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "PasswordChangeRequest{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
