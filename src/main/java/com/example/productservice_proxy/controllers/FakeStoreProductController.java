package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.clients.fakestore.dto.FakeStoreProductDto;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.FakeStoreProductService;
import com.example.productservice_proxy.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fakestore/products")
public class FakeStoreProductController {
    FakeStoreProductService productService;
    public FakeStoreProductController(FakeStoreProductService productService){
        this.productService = productService;
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId){
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept","application/json");
        headers.add("Content-Type", "application/json");
        headers.add("auth-token","heyaccess");
        try{
            Product product = this.productService.getSingleProduct(productId);
            if(productId<1){
                throw new IllegalArgumentException("Something went wrong");
            }
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(product,headers, HttpStatus.OK);
            return responseEntity;
        }
        catch(Exception e){
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(this.productService.getAllProduct(),HttpStatus.OK);
    }
    @PostMapping("")//create new item
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product){
        Product saveproduct = this.productService.addNewProduct(product);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(saveproduct,HttpStatus.OK);
        return responseEntity;
    }
    @PatchMapping("/{productId}")//update an item
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product){
        Product product1 = this.productService.updateProduct(productId, product);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product1,HttpStatus.OK);
        return responseEntity;
    }
    @PutMapping("{productId}") //update / replace an item
    public ResponseEntity<Product> putProduct(@PathVariable("productId") Long productId, @RequestBody Product product){
        Product product1 = this.productService.putProduct(productId, product);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product1,HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/{productId}")//delete an item
    public Product deleteProduct(@PathVariable("productId") Long productId){
        Product product = this.productService.deleteProduct(productId);
        return product;
    }
    @ExceptionHandler({NullPointerException.class,IllegalArgumentException.class})
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<>("kuch toh phat hai",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
