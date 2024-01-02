package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
@Service
public class ProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;
    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public Product getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                ProductDto.class,productId).getBody();
        Product product = new Product();
        getProduct(product, productDto);
        return product;
    }

    private static void getProduct(Product product, ProductDto productDto) {
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setId(productDto.getId());
        Categories categories = new Categories();
        categories.setName(productDto.getCategory());
        product.setCategory(categories);
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
    }

    @Override
    public String getAllProduct(){
        return "getting all products";
    }

    @Override
    public String addNewProduct(ProductDto productDto){
        return "Adding new product "+productDto;
    }

    @Override
    public String updateProduct(Long productId){
        return "updating product with id "+productId;
    }

    @Override
    public String patchProduct(Long productId){
        return "patching product with id "+productId;
    }

    @Override
    public String deleteProduct(Long productId){
        return "deleting product with id "+productId;
    }
}
