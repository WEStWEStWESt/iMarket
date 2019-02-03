package com.home_projects.imarket.dao.interfaces;

import com.home_projects.imarket.models.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
