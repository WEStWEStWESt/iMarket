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
@Table(name = "join_table")
@EqualsAndHashCode(callSuper = true)
public class JoinViewTable extends BaseEntity {

    @Column(name = "join_table_name")
    private String joinTableName;

    @Column(name = "join_table_number")
    private Integer joinTableNumber;

    @OneToMany(mappedBy = "joinTable")
    private Set<Field> fields;

    private Boolean chain;

}
