package com.hg.controller;


import com.hg.entity.Product;
import com.hg.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController
{
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

   public final static Logger log= LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/search")
    public List<?> searchProducts(@RequestParam String query)
    {
        log.info("Received search request with query: {}", query);
        return productService.searchProducts(query);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        log.info("Received request to get all products");
        return productService.getAllProducts();
    }
}