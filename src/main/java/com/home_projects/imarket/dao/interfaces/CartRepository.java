package com.home_projects.imarket.dao.interfaces;

import com.home_projects.imarket.models.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
