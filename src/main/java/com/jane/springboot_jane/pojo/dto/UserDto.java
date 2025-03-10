package com.jane.springboot_jane.pojo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class UserDto {
    @NotBlank(message = "User name cannot be null")
    @Length(min = 3, max = 100)
    private String name;

    private int age;

    @Email(message = "email format is invalid.")
    private String email;

    public @Email(message = "email format is invalid.")
    String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "email format is invalid.") String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
