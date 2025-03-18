package com.jane.springboot_jane.service;

import com.jane.springboot_jane.pojo.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

import com.jane.springboot_jane.pojo.User;
import com.jane.springboot_jane.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    // 创建用户
    public User createUser(UserDto user) {
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        return userRepository.save(newUser);
    }

    // 获取所有用户
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 根据 ID 获取用户
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // 更新用户信息
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setAge(userDetails.getAge());
            // if has password
            // user.setPassword(encoder.encode(user.getPassword))
            return userRepository.save(user);
        }
        return null;
    }

    // 删除用户
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}