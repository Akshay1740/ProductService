package com.example.productservice_proxy.services;

import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreProductDto;
import com.example.productservice_proxy.clients.fakestore.dto.IClientProductDto;
import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.repositories.ProductRepo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelfProductService implements IProductService{
    ProductRepo productRepo;
    public SelfProductService(ProductRepo productRepo){
        this.productRepo = productRepo;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        return productRepo.getProductById(productId);
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        return  this.productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product putProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) {
        productRepo.deleteById(productId);
        return null;
    }
}
