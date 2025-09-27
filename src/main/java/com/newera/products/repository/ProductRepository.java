package com.newera.products.repository;

import com.newera.products.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    
    // Find products by category
    List<Product> findByCategoryAndInStockTrueOrderByCreatedAtDesc(String category);
    
    // Find featured products
    List<Product> findByFeaturedTrueAndInStockTrueOrderByCreatedAtDesc();
    
    // Find products by category with pagination
    Page<Product> findByCategoryAndInStockTrue(String category, Pageable pageable);
    
    // Find all in-stock products with pagination
    Page<Product> findByInStockTrue(Pageable pageable);
    
    // Search products by name, description, or materials
    @Query("{'$and': [{'inStock': true}, {'$or': [{'name': {$regex: ?0, $options: 'i'}}, {'description': {$regex: ?0, $options: 'i'}}, {'materials': {$in: [{$regex: ?0, $options: 'i'}}]}]}]}")
    List<Product> searchProducts(String searchTerm);
    
    // Search products with pagination
    @Query("{'$and': [{'inStock': true}, {'$or': [{'name': {$regex: ?0, $options: 'i'}}, {'description': {$regex: ?0, $options: 'i'}}, {'materials': {$in: [{$regex: ?0, $options: 'i'}}]}]}]}")
    Page<Product> searchProducts(String searchTerm, Pageable pageable);
    
    // Find products by category and search term
    @Query("{'$and': [{'category': ?0}, {'inStock': true}, {'$or': [{'name': {$regex: ?1, $options: 'i'}}, {'description': {$regex: ?1, $options: 'i'}}, {'materials': {$in: [{$regex: ?1, $options: 'i'}}]}]}]}")
    List<Product> findByCategoryAndSearchTerm(String category, String searchTerm);
    
    // Find featured products with limit
    List<Product> findTop6ByFeaturedTrueAndInStockTrueOrderByCreatedAtDesc();
    
    // Count products by category
    long countByCategoryAndInStockTrue(String category);
    
    // Count all in-stock products
    long countByInStockTrue();
}
