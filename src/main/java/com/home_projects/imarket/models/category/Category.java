package com.home_projects.imarket.models.category;

import com.home_projects.imarket.models.BaseEntity;
import com.home_projects.imarket.models.group.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {

    @Column(name = "categoryname", nullable = false, unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private Set<Group> groups;
}
