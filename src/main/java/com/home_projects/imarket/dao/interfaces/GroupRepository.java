package com.home_projects.imarket.dao.interfaces;

import com.home_projects.imarket.models.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
