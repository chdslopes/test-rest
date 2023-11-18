package com.example.testrest.controller;
import com.example.testrest.model.Product;
import com.example.testrest.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnListOfProducts() {
        // Arrange
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(productList);

        // Act
        ResponseEntity<List<Product>> responseEntity = productController.findAll();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productList, responseEntity.getBody());
    }

    @Test
    void findById_shouldReturnProductById() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(productService.findById(productId)).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<Optional<Product>> responseEntity = productController.findById(productId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Optional.of(product), responseEntity.getBody());
    }

    @Test
    void create_shouldReturnCreatedProduct() {
        // Arrange
        Product product = new Product();
        when(productService.save(product)).thenReturn(product);

        // Act
        ResponseEntity<Product> responseEntity = productController.create(product);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }

    @Test
    void update_shouldReturnUpdatedProduct() {
        // Arrange
        Product product = new Product();
        when(productService.update(product)).thenReturn(product);

        // Act
        ResponseEntity<Product> responseEntity = productController.update(product);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }

    @Test
    void delete_shouldDeleteProductById() {
        // Arrange
        Long productId = 1L;

        // Act
        ResponseEntity<?> responseEntity = productController.delete(productId);

        // Assert
        verify(productService, times(1)).deleteById(productId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
