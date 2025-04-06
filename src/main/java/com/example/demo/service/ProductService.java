package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final S3Service s3Service;
    private final KafkaTemplate<String, ProductDto> kafkaTemplate;
    private static final String TOPIC = "demo.products";

    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        sendProductToKafka(productDto);
        return productRepository.save(product);
    }

    public void sendProductToKafka(ProductDto productDto) {
        kafkaTemplate.send(TOPIC, productDto);
    }
}