package com.home_projects.imarket.dao.interfaces;

import com.home_projects.imarket.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
