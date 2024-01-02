package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.IProductService;
import com.example.productservice_proxy.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    IProductService productService;
    public ProductController(IProductService productService){
        this.productService = productService;
    }
    @GetMapping("/{productId}")
    public Product getSingleProduct(@PathVariable("productId") Long productId){
        Product product = this.productService.getSingleProduct(productId);
        return product;
    }
    @GetMapping("")
    public String getAllProduct(){
        return "getting all products";
    }
    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto){
        return "Adding new product "+productDto;
    }
    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId){
        return "updating product with id "+productId;
    }
    @PatchMapping("{productId}")
    public String patchProduct(@PathVariable("productId") Long productId){
       return "patching product with id "+productId;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "deleting product with id "+productId;
    }
}
