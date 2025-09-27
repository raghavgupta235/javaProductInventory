package com.newera.products.service;

import com.newera.products.model.Product;
import com.newera.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    // Get all products with pagination and optional filtering
    public Page<Product> getAllProducts(String category, Boolean featured, String search, int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        
        if (search != null && !search.trim().isEmpty()) {
            if (category != null && !category.trim().isEmpty()) {
                List<Product> products = productRepository.findByCategoryAndSearchTerm(category, search);
                return Page.empty(pageable);
            } else {
                return productRepository.searchProducts(search, pageable);
            }
        }
        
        if (category != null && !category.trim().isEmpty()) {
            return productRepository.findByCategoryAndInStockTrue(category, pageable);
        }
        
        if (featured != null && featured) {
            List<Product> featuredProducts = productRepository.findByFeaturedTrueAndInStockTrueOrderByCreatedAtDesc();
            return Page.empty(pageable);
        }
        
        return productRepository.findByInStockTrue(pageable);
    }
    
    // Get featured products
    public List<Product> getFeaturedProducts(int limit) {
        if (limit <= 0) {
            limit = 6;
        }
        return productRepository.findTop6ByFeaturedTrueAndInStockTrueOrderByCreatedAtDesc();
    }
    
    // Get products by category
    public List<Product> getProductsByCategory(String category, int limit) {
        if (limit <= 0) {
            limit = 20;
        }
        return productRepository.findByCategoryAndInStockTrueOrderByCreatedAtDesc(category);
    }
    
    // Get single product by ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
    
    // Create new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    // Update product
    public Optional<Product> updateProduct(String id, Product productDetails) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDetails.getName());
            product.setCategory(productDetails.getCategory());
            product.setPrice(productDetails.getPrice());
            product.setDescription(productDetails.getDescription());
            product.setSpecifications(productDetails.getSpecifications());
            product.setImages(productDetails.getImages());
            product.setFeatured(productDetails.getFeatured());
            product.setInStock(productDetails.getInStock());
            product.setDimensions(productDetails.getDimensions());
            product.setMaterials(productDetails.getMaterials());
            product.setCloudinaryId(productDetails.getCloudinaryId());
            product.setUpdatedAt(java.time.LocalDateTime.now());
            
            return Optional.of(productRepository.save(product));
        }
        
        return Optional.empty();
    }
    
    // Delete product
    public boolean deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Search products
    public List<Product> searchProducts(String searchTerm) {
        return productRepository.searchProducts(searchTerm);
    }
    
    // Get total count of products
    public long getTotalProductCount() {
        return productRepository.countByInStockTrue();
    }
    
    // Get count by category
    public long getProductCountByCategory(String category) {
        return productRepository.countByCategoryAndInStockTrue(category);
    }
}
