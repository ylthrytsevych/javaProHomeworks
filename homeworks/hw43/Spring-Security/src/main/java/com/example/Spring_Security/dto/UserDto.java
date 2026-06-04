package com.example.Spring_Security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

// Клас DTO (Data Transfer Object)
// для передачі даних між клієнтом і сервером.
// Клас UserDto для передачі даних між
// рівнем контролера та рівнем презентації.
// Клас використовується для зв'язування даних
// з формою у презентації.
public class UserDto {

    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Phone should not be empty")
    private String phone;

//    @NotEmpty(message = "First name should not be empty")
//    private String firstName;
//
//    @NotEmpty(message = "Last name should not be empty")
//    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    public UserDto() {
    }

    public UserDto(Long id, String name, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
//        this.firstName = firstName;
//        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
