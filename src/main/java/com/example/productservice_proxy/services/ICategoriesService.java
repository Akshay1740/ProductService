package com.example.productservice_proxy.services;

import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;

import java.util.List;

public interface ICategoriesService {
    Categories addNewCategory(Categories categories);

    List<Product> getProductsByCategory(Long categoryId);

    List<Categories> getAllCategories();
}
