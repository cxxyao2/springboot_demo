package com.jane.springboot_jane.service;


import com.jane.springboot_jane.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.jane.springboot_jane.pojo.Product;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    public Product updateProduct(Long id, Product ProductDetails) {
        Product Product = productRepository.findById(id).orElse(null);
        if (Product != null) {
            Product.setName(ProductDetails.getName());
            Product.setPrice(ProductDetails.getPrice());
            return productRepository.save(Product);
        }
        return null;
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}