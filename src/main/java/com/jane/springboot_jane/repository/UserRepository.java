package com.jane.springboot_jane.repository;

import com.jane.springboot_jane.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}