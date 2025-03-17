package com.jane.springboot_jane.repository;

import com.jane.springboot_jane.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
