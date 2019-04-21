package com.home_projects.imarket.models.view;

import com.home_projects.imarket.models.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "field")
@EqualsAndHashCode(callSuper = true)
public class Field extends BaseEntity {

    @Column(name = "field_name")
    private String fieldName;

    private Boolean sortable;

    @Column(name = "field_number")
    private Integer fieldNumber;

    @ManyToOne
    @JoinColumn(name = "filter_id")
    private Filter filter;

    @ManyToOne
    @JoinColumn(name = "main_id")
    private MainViewTable mainViewTable;

    @ManyToOne
    @JoinColumn(name = "join_id")
    private JoinViewTable joinViewTable;

}
