package com.restapi.swagger.controller.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.restapi.swagger.dto.ProductDTO;
import com.restapi.swagger.model.Product;
import com.restapi.swagger.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Product Management", description = "API Toko Paduka Yudis")
public class MainController {

    @Autowired
    private ProductService productService;



    // Untuk konversi antara DTO dan Entity
    private Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        if (dto.getId() != null) {
            product.setId(dto.getId());
        }
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setStockQuantity(dto.getStockQuantity());
        product.setPictureUrl(dto.getPictureUrl());
        return product;
    }

    // Untuk konversi antara Entity dan DTO
    private ProductDTO convertToDto(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setCategory(entity.getCategory());
        dto.setStockQuantity(entity.getStockQuantity());
        dto.setPictureUrl(entity.getPictureUrl());
        return dto;
    }

    // Ambil semua data produk
    @GetMapping("/getData")
    @Operation(summary = "Ambil Data Produk")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Ambil data produk per page
    @GetMapping("/getDataPage")
    @Operation(summary = "Ambil Data Produk (Pageable)")
    public Map<String, Object> getAllProducts(@RequestParam(defaultValue = "1") int page) {
        int size = 25;
        int rpage = (page < 1) ? 0 : page - 1;
        Page<Product> pageResult = productService.getAllProductsPage(rpage, size);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total_page", pageResult.getTotalPages());
        response.put("prev_page", pageResult.hasPrevious() ? "/api/getDataPage?page=" + (page - 1) : null);
        response.put("current_page", pageResult.getNumber() + 1);
        response.put("next_page", pageResult.hasNext() ? "/api/getDataPage?page=" + (page+1) : null);
        response.put("total_data", pageResult.getTotalElements());
        response.put("data", pageResult.getContent());
        return response;
    }

    // Search produk by name
    @GetMapping("/searchByName")
    @Operation(summary = "Search Produk by Name")
    public List<Product> searchProductsByName(@RequestParam String keyword) {
        return productService.searchProductByName(keyword);
    }

    // Search produk by category
    @GetMapping("/searchByCategory")
    @Operation(summary = "Search Produk by Category")
    public List<Product> searchProductsByCategory(@RequestParam String keyword) {
        return productService.searchProductByCategory(keyword);
    }


    // Add new product
    @PostMapping(value = "/addData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Tambah Produk (Form DTO)", description = "Input via Form Field menggunakan DTO")
    public ProductDTO addProduct(@ModelAttribute ProductDTO productDto) { 
        Product productEntity = convertToEntity(productDto);
        Product savedProduct = productService.saveProduct(productEntity);
        return convertToDto(savedProduct);
    }

    // Update data product
    @PutMapping(value = "/updateData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update Produk (Form DTO)", description = "Update via Form Field menggunakan DTO")
    public ProductDTO updateProduct(@ModelAttribute ProductDTO productDto) {
        Product productEntity = convertToEntity(productDto);
        Product updatedProduct = productService.updateProduct(productDto.getId(), productEntity);        
        return convertToDto(updatedProduct);
    }

    // Hapus data product
    @DeleteMapping(value = "/deleteData")
    @Operation(summary = "Hapus Produk", description = "Hapus produk berdasarkan ID")
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
    }
}