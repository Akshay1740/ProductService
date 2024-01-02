package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDto;

public interface IProductService {
    String getSingleProduct(Long productId);

    String getAllProduct();

    String addNewProduct(ProductDto productDto);

    String updateProduct(Long productId);

    String patchProduct(Long productId);

    String deleteProduct(Long productId);
}
