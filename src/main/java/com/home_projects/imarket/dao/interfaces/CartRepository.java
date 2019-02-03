package com.home_projects.imarket.dao.interfaces;

import com.home_projects.imarket.models.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Set;

public interface CartRepository extends JpaRepository<Cart, Long> {
    /*@Query(nativeQuery = true, value = "SELECT * FROM carts")
    Set<Cart> findByClosedDateOrderByProfileAsc(@Param("closed_date") Timestamp closedDate);*/

}
