/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager;

/**
 *
 * @author Hype
 */
public class User {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String role;

    public User(String userId, String username, String password, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public void register(String userId, String username, String password, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        System.out.println("Akun berhasil dibuat.");
    }

    public boolean login(String inputUsername, String inputPassword) {
        if (this.username.equals(inputUsername) && this.password.equals(inputPassword)) {
            System.out.println("Login berhasil.");
            return true;
        } else {
            System.out.println("Username atau password salah.");
            return false;
        }
    }

    public void logout() {
        System.out.println("Logout berhasil.");
    }

    public void updateProfile(String newEmail, String newPassword) {
        this.email = newEmail;
        this.password = newPassword;
        System.out.println("Profil berhasil diperbarui.");
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
