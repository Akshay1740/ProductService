package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Product;

public interface IProductService {
    Product getSingleProduct(Long productId);

    String getAllProduct();

    String addNewProduct(ProductDto productDto);

    String updateProduct(Long productId);

    String patchProduct(Long productId);

    String deleteProduct(Long productId);
}
