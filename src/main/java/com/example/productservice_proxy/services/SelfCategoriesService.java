package com.example.productservice_proxy.services;

import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import com.example.productservice_proxy.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfCategoriesService implements ICategoriesService {
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public Categories addNewCategory(Categories categories){
        return categoryRepo.save(categories);
    }
    @Override
    public List<Product> getProductsByCategory(Long categoryId){
        Categories categories = categoryRepo.getCategoriesById(categoryId);
        return categories.getProductList();
    }
    @Override
    public List<Categories> getAllCategories(){
        return categoryRepo.findAll();
    }
}
