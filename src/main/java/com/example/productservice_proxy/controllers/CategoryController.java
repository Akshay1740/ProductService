package com.example.productservice_proxy.controllers;

import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.services.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    @Autowired
    ICategoriesService iCategoriesService;

    @GetMapping("")
    public List<Categories> getAllCategories(){
        return iCategoriesService.getAllCategories();
    }
    @GetMapping("/{categoryId}")
    public List<Product> getProductsInCategories(@PathVariable("categoryId") Long categoryId) throws Exception {
        if(categoryId<1) throw new Exception("Invalid Category Id");
        return iCategoriesService.getProductsByCategory(categoryId);
    }
    @PostMapping("")
    public Categories addNewCategory(@RequestBody Categories categories){
        return iCategoriesService.addNewCategory(categories);
    }
}
