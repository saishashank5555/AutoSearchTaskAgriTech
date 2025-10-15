package com.hg.service;

import com.hg.entity.Product;

import java.util.List;

public interface IProductService {

    public List<String> searchProducts(String query);

    public List<Product> getAllProducts();
}
