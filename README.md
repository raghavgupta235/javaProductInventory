# Products Inventory API - Spring Boot

A Spring Boot REST API for managing products inventory with MongoDB integration.

## Features

- RESTful API for product management
- MongoDB integration with Spring Data
- CORS enabled for frontend integration
- Product search and filtering
- Featured products endpoint
- Category-based product filtering
- Pagination support
- Health check endpoint
- Input validation
- Comprehensive error handling

## API Endpoints

### Products
- `GET /api/products` - Get all products (with optional filtering)
- `GET /api/products/featured` - Get featured products
- `GET /api/products/category/{category}` - Get products by category
- `GET /api/products/{id}` - Get single product
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Health Check
- `GET /health` - Server health check with database status

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data MongoDB**
- **Maven**
- **MongoDB**
- **Spring Boot Actuator**

## Installation

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MongoDB Atlas account or local MongoDB

### 1. Clone and Setup
```bash
cd /Users/raghav/Documents/javaProductInventory
```

### 2. Configure Environment
Set environment variables or update `application.yml`:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://username:password@cluster.mongodb.net/productsInventory
```

### 3. Build and Run
```bash
# Development
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Production
mvn spring-boot:run -Dspring-boot.run.profiles=prod

# Or build JAR
mvn clean package
java -jar target/products-inventory-1.0.0.jar
```

## Environment Variables

- `MONGODB_URI` - MongoDB connection string
- `NODE_ENV` - Environment (development/production)
- `PORT` - Server port (default: 3001)

## Project Structure

```
src/
├── main/
│   ├── java/com/newera/products/
│   │   ├── ProductsInventoryApplication.java
│   │   ├── controller/
│   │   │   ├── ProductController.java
│   │   │   └── HealthController.java
│   │   ├── model/
│   │   │   └── Product.java
│   │   ├── repository/
│   │   │   └── ProductRepository.java
│   │   └── service/
│   │       └── ProductService.java
│   └── resources/
│       ├── application.yml
│       ├── application-dev.yml
│       └── application-prod.yml
└── test/
    └── java/com/newera/products/
```

## API Examples

### Get All Products
```bash
curl http://localhost:3001/api/products
```

### Get Featured Products
```bash
curl http://localhost:3001/api/products/featured
```

### Create Product
```bash
curl -X POST http://localhost:3001/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Product",
    "category": "coffee-tables",
    "price": 25.99,
    "description": "A test product",
    "images": ["https://example.com/image.jpg"],
    "materials": ["resin", "pigment"],
    "featured": false,
    "inStock": true
  }'
```

### Health Check
```bash
curl http://localhost:3001/health
```

## Deployment

### Traditional Deployment
- Build JAR: `mvn clean package`
- Run: `java -jar target/products-inventory-1.0.0.jar`

### Docker (Optional)
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/products-inventory-1.0.0.jar app.jar
EXPOSE 3001
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Cloud Platforms
- **Heroku**: Use `Procfile` with `java -jar target/products-inventory-1.0.0.jar`
- **AWS**: Deploy to EC2 or Elastic Beanstalk
- **Google Cloud**: Use Cloud Run or App Engine
- **Azure**: Use App Service

## Testing

```bash
# Run tests
mvn test

# Run with coverage
mvn test jacoco:report
```

## Monitoring

- **Health Check**: `/health`
- **Actuator Endpoints**: `/actuator/health`, `/actuator/info`, `/actuator/metrics`
- **Logs**: Configured for different environments

## Migration from Node.js

This Spring Boot application provides the same API endpoints as the original Node.js version:

| Node.js Endpoint | Spring Boot Endpoint | Status |
|------------------|---------------------|---------|
| `GET /api/products` | `GET /api/products` | ✅ |
| `GET /api/products/featured` | `GET /api/products/featured` | ✅ |
| `GET /api/products/category/:category` | `GET /api/products/category/{category}` | ✅ |
| `GET /api/products/:id` | `GET /api/products/{id}` | ✅ |
| `POST /api/products` | `POST /api/products` | ✅ |
| `PUT /api/products/:id` | `PUT /api/products/{id}` | ✅ |
| `DELETE /api/products/:id` | `DELETE /api/products/{id}` | ✅ |
| `GET /health` | `GET /health` | ✅ |

## License

ISC License - New Era Resin Arts
