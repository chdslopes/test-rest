package com.example.testrest.service;

import com.example.testrest.model.Product;
import com.example.testrest.repository.ProductRepository;
import com.example.testrest.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_shouldReturnSavedProduct() {
        // Arrange
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product savedProduct = productService.save(product);

        // Assert
        assertEquals(product, savedProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void findAll_shouldReturnListOfProducts() {
        // Arrange
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<Product> result = productService.findAll();

        // Assert
        assertEquals(productList.size(), result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void findById_shouldReturnProductById() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> result = productService.findById(productId);

        // Assert
        assertEquals(Optional.of(product), result);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void update_shouldReturnUpdatedProduct() {
        // Arrange
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product updatedProduct = productService.update(product);

        // Assert
        assertEquals(product, updatedProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void deleteById_shouldDeleteProductById() {
        // Arrange
        Long productId = 1L;

        // Act
        productService.deleteById(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(productId);
    }
}
