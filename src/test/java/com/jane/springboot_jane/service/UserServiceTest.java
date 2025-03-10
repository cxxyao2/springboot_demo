package com.jane.springboot_jane.service;

import com.jane.springboot_jane.pojo.User;
import com.jane.springboot_jane.pojo.dto.UserDto;
import com.jane.springboot_jane.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        // 准备测试数据
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setAge(30);
        userDto.setEmail("john.doe@example.com");

        User newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);
        newUser.setId(1L);

        // 模拟 repository 的行为
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        // 调用服务方法
        User createdUser = userService.createUser(userDto);

        // 验证结果
        assertNotNull(createdUser);
        assertEquals(newUser.getId(), createdUser.getId());
        assertEquals(newUser.getName(), createdUser.getName());
        assertEquals(newUser.getAge(), createdUser.getAge());
        assertEquals(newUser.getEmail(), createdUser.getEmail());

        // 验证 repository 方法是否被调用
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetAllUsers() {
        // 准备测试数据
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");
        user1.setAge(30);
        userList.add(user1);

        // 模拟 repository 的行为
        when(userRepository.findAll()).thenReturn(userList);

        // 调用服务方法
        List<User> result = userService.getAllUsers();

        // 验证结果
        assertNotNull(result);
        assertEquals(userList.size(), result.size());

        // 验证 repository 方法是否被调用
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        // 准备测试数据
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("John Doe");
        user.setAge(30);

        // 模拟 repository 的行为
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // 调用服务方法
        User result = userService.getUserById(userId);

        // 验证结果
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getAge(), result.getAge());

        // 验证 repository 方法是否被调用
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetUserByIdNotFound() {
        // 准备测试数据
        Long userId = 1L;

        // 模拟 repository 的行为
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // 调用服务方法
        User result = userService.getUserById(userId);

        // 验证结果
        assertNull(result);

        // 验证 repository 方法是否被调用
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testUpdateUser() {
        // 准备测试数据
        Long userId = 1L;
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setName("Old Name");
        existingUser.setAge(25);

        User updatedUserDetails = new User();
        updatedUserDetails.setName("New Name");
        updatedUserDetails.setAge(30);

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setName("New Name");
        updatedUser.setAge(30);

        // 模拟 repository 的行为
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // 调用服务方法
        User result = userService.updateUser(userId, updatedUserDetails);

        // 验证结果
        assertNotNull(result);
        assertEquals(updatedUser.getId(), result.getId());
        assertEquals(updatedUser.getName(), result.getName());
        assertEquals(updatedUser.getAge(), result.getAge());

        // 验证 repository 方法是否被调用
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUserNotFound() {
        // 准备测试数据
        Long userId = 1L;
        User updatedUserDetails = new User();
        updatedUserDetails.setName("New Name");
        updatedUserDetails.setAge(30);

        // 模拟 repository 的行为
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // 调用服务方法
        User result = userService.updateUser(userId, updatedUserDetails);

        // 验证结果
        assertNull(result);

        // 验证 repository 方法是否被调用
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testDeleteUser() {
        // 准备测试数据
        Long userId = 1L;

        // 调用服务方法
        userService.deleteUser(userId);

        // 验证 repository 方法是否被调用
        verify(userRepository, times(1)).deleteById(userId);
    }
}