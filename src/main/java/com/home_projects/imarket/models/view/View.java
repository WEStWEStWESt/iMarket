package com.home_projects.imarket.models.view;

import com.home_projects.imarket.models.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "view")
@EqualsAndHashCode(callSuper = true)
public class View extends BaseEntity {

    @Column(name = "view_description")
    private String viewDescription;

    @OneToMany(mappedBy = "view")
    private Set<ViewColumn> columns;

}



