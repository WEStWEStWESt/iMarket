package com.home_projects.imarket.models.group;

import com.home_projects.imarket.models.BaseEntity;
import com.home_projects.imarket.models.category.Category;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
@EqualsAndHashCode(callSuper = true)
public class Group extends BaseEntity {

    @Column(name = "groupname", nullable = false, unique = true)
    private String groupName;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "category_id")
    private Category category;
}
