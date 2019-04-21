package com.home_projects.imarket.models.view;

import com.home_projects.imarket.models.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "field")
@EqualsAndHashCode(callSuper = true)
public class Field extends BaseEntity {

    @Column(name = "field_name")
    private String fieldName;

    private Boolean sortable;

    @Column(name = "field_number")
    private Integer fieldNumber;

    @OneToOne
    @JoinColumn(name = "filter_id")
    private Filter filter;

    @ManyToOne
    @JoinColumn(name = "main_id")
    private MainViewTable mainTable;

    @ManyToOne
    @JoinColumn(name = "join_id")
    private JoinViewTable joinTable;

    @Builder
    public Field(Long id, String fieldName, Boolean sortable, Integer fieldNumber, Filter filter, MainViewTable mainTable, JoinViewTable joinTable) {
        setId(id);
        this.fieldName = fieldName;
        this.sortable = sortable;
        this.fieldNumber = fieldNumber;
        this.filter = filter;
        this.mainTable = mainTable;
        this.joinTable = joinTable;
    }

}
