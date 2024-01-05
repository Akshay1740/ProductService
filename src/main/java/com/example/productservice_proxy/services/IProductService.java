package com.example.productservice_proxy.services;

import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreProductDto;
import com.example.productservice_proxy.clients.fakestore.dto.IClientProductDto;
import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Product;

import java.util.List;

public interface IProductService {
    Product getSingleProduct(Long productId);

    List<Product> getAllProduct();

    Product addNewProduct(Product productDto);

    Product updateProduct(Long productId, Product product);

    Product putProduct(Long productId, Product product);

    Product deleteProduct(Long productId);
}
