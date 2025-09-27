package com.newera.products.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "products")
public class Product {
    
    @Id
    private String id;
    
    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Product name cannot exceed 100 characters")
    private String name;
    
    @NotBlank(message = "Category is required")
    @Pattern(regexp = "^(coffee-tables|center-tables|wall-clocks)$", 
             message = "Category must be one of: coffee-tables, center-tables, wall-clocks")
    private String category;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    private Double price;
    
    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;
    
    private List<String> specifications;
    
    @NotEmpty(message = "At least one image is required")
    private List<String> images;
    
    @Indexed
    private Boolean featured = false;
    
    @Indexed
    private Boolean inStock = true;
    
    private Dimensions dimensions;
    
    private List<String> materials;
    
    private String cloudinaryId;
    
    @Indexed
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    // Constructors
    public Product() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public Product(String name, String category, Double price, String description, List<String> images) {
        this();
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.images = images;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<String> getSpecifications() {
        return specifications;
    }
    
    public void setSpecifications(List<String> specifications) {
        this.specifications = specifications;
    }
    
    public List<String> getImages() {
        return images;
    }
    
    public void setImages(List<String> images) {
        this.images = images;
    }
    
    public Boolean getFeatured() {
        return featured;
    }
    
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }
    
    public Boolean getInStock() {
        return inStock;
    }
    
    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }
    
    public Dimensions getDimensions() {
        return dimensions;
    }
    
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }
    
    public List<String> getMaterials() {
        return materials;
    }
    
    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }
    
    public String getCloudinaryId() {
        return cloudinaryId;
    }
    
    public void setCloudinaryId(String cloudinaryId) {
        this.cloudinaryId = cloudinaryId;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    // Virtual getters
    public String getFormattedPrice() {
        return String.format("$%.2f", price);
    }
    
    public String getPrimaryImage() {
        return images != null && !images.isEmpty() ? images.get(0) : null;
    }
    
    // Inner class for dimensions
    public static class Dimensions {
        @DecimalMin(value = "0.0", message = "Length cannot be negative")
        private Double length;
        
        @DecimalMin(value = "0.0", message = "Width cannot be negative")
        private Double width;
        
        @DecimalMin(value = "0.0", message = "Height cannot be negative")
        private Double height;
        
        // Constructors
        public Dimensions() {}
        
        public Dimensions(Double length, Double width, Double height) {
            this.length = length;
            this.width = width;
            this.height = height;
        }
        
        // Getters and Setters
        public Double getLength() {
            return length;
        }
        
        public void setLength(Double length) {
            this.length = length;
        }
        
        public Double getWidth() {
            return width;
        }
        
        public void setWidth(Double width) {
            this.width = width;
        }
        
        public Double getHeight() {
            return height;
        }
        
        public void setHeight(Double height) {
            this.height = height;
        }
    }
}
