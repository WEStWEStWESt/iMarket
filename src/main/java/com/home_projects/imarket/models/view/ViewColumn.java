package com.home_projects.imarket.models.view;

import com.home_projects.imarket.models.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "view_column")
@EqualsAndHashCode(callSuper = true)
public class ViewColumn extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "view_id", nullable = false)
    private View view;

    private String header;

    @OneToOne
    @JoinColumn(name = "field_id")
    private Field field;

}
