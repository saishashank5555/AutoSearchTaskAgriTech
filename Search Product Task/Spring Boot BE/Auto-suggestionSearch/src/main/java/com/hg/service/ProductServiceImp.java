package com.hg.service;

import com.hg.entity.Product;
import com.hg.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements IProductService
{
    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImp.class);
    @Override
    public List<String> searchProducts(String query) {

        log.info("Searching products with query: {}", query);

        List<Product> byNameStartingWithIgnoreCase = productRepository.findByNameStartingWithIgnoreCase(query);

        List<String> listOfNames = byNameStartingWithIgnoreCase.stream()
                .map(p -> p.getName())
                .collect(Collectors.toList());

        log.info("Found products: {}", listOfNames);
        return listOfNames;
    }

    @Override
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        List<Product> getallProducts = productRepository.findAll();
        log.info("Total products found: {}", getallProducts.size());
        return getallProducts;
    }
}
