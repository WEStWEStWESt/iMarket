package com.home_projects.imarket.dao.interfaces;

import com.home_projects.imarket.models.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
