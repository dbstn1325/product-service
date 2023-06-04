package com.example.itemservice.repository;

import com.example.itemservice.dto.FilterProductRequest;
import com.example.itemservice.entity.Product;

import java.util.List;

public interface ProductFilterRepository {
    List<Product> filterProducts (FilterProductRequest productDto);
}
