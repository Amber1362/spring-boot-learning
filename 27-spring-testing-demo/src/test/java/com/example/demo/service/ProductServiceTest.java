package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnProductWhenProductExist() {

        //arrange
        Product product = new Product(1L, "Laptop", 50000, 10);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        //act
        Product actualResult = productService.getProductById(1L);

        //assert
        assertEquals(1L, actualResult.getId());
        assertEquals("Laptop", actualResult.getName());
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {

        //arrange
        when(productRepository.findById(19L))
                .thenReturn(Optional.empty());

        //action
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                productService.getProductById(19L));

        //assertion
        assertEquals("Product not found: 19", exception.getMessage());
    }

    @Test
    void shouldCreateWhenNameIsUnique() {

        //arrange
        Product request = new Product(null, "Keyboard", 200, 10);

        Product savedProduct = new Product(10L, "Keyboard", 200, 10);

        when(productRepository.existsByName("Keyboard"))
                .thenReturn(false);

        when(productRepository.save(request))
                .thenReturn(savedProduct);

        //act
        Product result = productService.createProduct(request);

        //assert
        assertEquals(10L, result.getId());
        assertEquals("Keyboard", result.getName());


    }
}
