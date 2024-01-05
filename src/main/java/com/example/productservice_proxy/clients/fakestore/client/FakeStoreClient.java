package com.example.productservice_proxy.clients.fakestore.client;

import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreProductDto;
import com.example.productservice_proxy.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public List<FakeStoreProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> productDtos = restTemplate.getForEntity("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<FakeStoreProductDto> productDtoList = List.of(productDtos.getBody());
        return productDtoList;
    }

    public FakeStoreProductDto getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class,productId);
        return productDto.getBody();
    }
    public void deleteProduct(Long productId){
        String fakestoreUrl = "https://fakestoreapi.com/products/"+productId;
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(fakestoreUrl);
    }
}
