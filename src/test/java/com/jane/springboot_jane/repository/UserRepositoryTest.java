package com.jane.springboot_jane.repository;

import com.jane.springboot_jane.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("Test User");
        user.setAge(25);
        user.setEmail("tom@gmail.com");

        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setName("Test User");
        user.setAge(25);
        user.setEmail("tom@gmail.com");
        User savedUser = userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertTrue(foundUser.isPresent());
        assertEquals(savedUser.getName(), foundUser.get().getName());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setName("Test User");
        user.setAge(25);
        user.setAge(25);
        user.setEmail("tom@gmail.com");
        User savedUser = userRepository.save(user);

        userRepository.deleteById(savedUser.getId());
        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertFalse(foundUser.isPresent());
    }
}