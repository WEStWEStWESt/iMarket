package com.home_projects.imarket.models.view;

import com.home_projects.imarket.models.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
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

    @OneToOne
    @JoinColumn(name = "main_id")
    private MainViewTable mainTable;

    @OneToMany(mappedBy = "view")
    private Set<JoinViewTable> joinTables;

}



