package com.home_projects.imarket.models.view;

import com.home_projects.imarket.models.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "filter")
@EqualsAndHashCode(callSuper = true)
public class Filter extends BaseEntity {

    private String operator;

    private String value;

    @OneToOne(mappedBy = "filter")
    private Field field;

}
