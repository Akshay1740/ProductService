package com.example.productservice_proxy.services;

import com.example.productservice_proxy.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

public class ProductService implements IProductService {

    @Override
    public String getSingleProduct(Long productId){
        return "Returning single product with id "+productId;
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
