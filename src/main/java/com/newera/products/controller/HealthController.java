package com.newera.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class HealthController {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Test MongoDB connection
            boolean dbConnected = false;
            try {
                mongoTemplate.getCollectionNames();
                dbConnected = true;
            } catch (Exception e) {
                dbConnected = false;
            }
            
            response.put("status", dbConnected ? "OK" : "WARNING");
            response.put("message", dbConnected ? "Products Inventory Server is running" : "Server running but database connection pending");
            response.put("timestamp", LocalDateTime.now().toString());
            
            Map<String, Object> database = new HashMap<>();
            database.put("status", dbConnected ? "connected" : "disconnected");
            database.put("connected", dbConnected);
            response.put("database", database);
            
            response.put("environment", System.getProperty("spring.profiles.active", "development"));
            response.put("uptime", System.currentTimeMillis() - getStartTime());
            
            return ResponseEntity.status(dbConnected ? 200 : 503).body(response);
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Health check failed");
            errorResponse.put("timestamp", LocalDateTime.now().toString());
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(503).body(errorResponse);
        }
    }
    
    private long getStartTime() {
        return System.currentTimeMillis() - java.lang.management.ManagementFactory.getRuntimeMXBean().getUptime();
    }
}
