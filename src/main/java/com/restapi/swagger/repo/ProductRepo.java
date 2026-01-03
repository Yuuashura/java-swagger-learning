package com.restapi.swagger.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.restapi.swagger.model.Product;


public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findByNameContainingIgnoreCase(String k);
    List<Product> findByCategoryContainingIgnoreCaseOrderByIdAsc(String k);
    Page<Product> findAllByOrderByIdAsc(Pageable pageable);

}