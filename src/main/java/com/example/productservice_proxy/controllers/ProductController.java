package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId){
        return "Returning single product with id "+productId;
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
