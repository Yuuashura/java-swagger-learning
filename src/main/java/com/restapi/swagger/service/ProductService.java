package com.restapi.swagger.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service; // WAJIB ADA
import com.restapi.swagger.model.Product;
import com.restapi.swagger.repo.ProductRepo;
import org.springframework.data.domain.Page;

@Service // Anotasi Penyelamat
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    // Harus Public
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // Tambahan buat seeder
    public void saveAll(List<Product> products) {
        productRepo.saveAll(products);
    }

    // Penghitung data awal
    public long count() {
        return productRepo.count();
    }

    // Simpan product
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    // Hapus product by ID
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    // Cari product by Name
    public List<Product> searchProductByName(String keyword) {
        return productRepo.findByNameContainingIgnoreCase(keyword);
    }

    // Cari product by Category
    public List<Product> searchProductByCategory(String keyword) {
        return productRepo.findByCategoryContainingIgnoreCaseOrderByIdAsc(keyword);
    }

    // Update Product
    public Product updateProduct(Long id, Product productBaru) {
        Product produkLama = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product dengan ID " + id + " tidak ditemukan."));
        produkLama.setName(productBaru.getName());
        produkLama.setPrice(productBaru.getPrice());
        produkLama.setDescription(productBaru.getDescription());
        produkLama.setCategory(productBaru.getCategory());
        produkLama.setStockQuantity(productBaru.getStockQuantity());
        produkLama.setPictureUrl(productBaru.getPictureUrl());

        return productRepo.save(produkLama);
    }

public Page<Product> getAllProductsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.findAllByOrderByIdAsc(pageable);
    }

}