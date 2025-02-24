package com.jane.springboot_jane.service;

import com.jane.springboot_jane.dao.User;
import com.jane.springboot_jane.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("Test User");
        user.setAge(25);

        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);
        assertEquals(user.getName(), createdUser.getName());
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setName("User 1");
        user1.setAge(20);
        User user2 = new User();
        user2.setName("User 2");
        user2.setAge(22);

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setAge(25);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);
        assertNotNull(foundUser);
        assertEquals(user.getName(), foundUser.getName());
    }

    @Test
    public void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Old Name");
        existingUser.setAge(25);

        User updatedUserDetails = new User();
        updatedUserDetails.setName("New Name");
        updatedUserDetails.setAge(26);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.updateUser(1L, updatedUserDetails);
        assertEquals("New Name", result.getName());
        assertEquals(26, result.getAge());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}