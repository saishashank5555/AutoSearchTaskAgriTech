package com.hg.repository;

import com.hg.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface ProductRepository  extends JpaRepository<Product,Long>
{
    List<Product> findByNameStartingWithIgnoreCase(String query);
}
