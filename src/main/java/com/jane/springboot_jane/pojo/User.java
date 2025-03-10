package com.jane.springboot_jane.pojo;

import jakarta.persistence.*;

@Table
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String email;

    @Column(nullable = true)
    private String personal_privacy;


    // 无参构造函数
    public User() {}

    // 有参构造函数
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter 和 Setter 方法


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPersonal_privacy() {
        return personal_privacy;
    }

    public void setPersonal_privacy(String personal_privacy) {
        this.personal_privacy = personal_privacy;
    }
}
