package com.home_projects.imarket.models.view;

import com.home_projects.imarket.models.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "main_table")
@EqualsAndHashCode(callSuper = true)
public class MainViewTable extends BaseEntity {

    @Column(name = "main_table_name")
    private String mainTableName;

    @OneToMany(mappedBy = "mainTable")
    private Set<Field> fields;

}
