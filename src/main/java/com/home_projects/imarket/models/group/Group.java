package com.home_projects.imarket.models.group;

import com.home_projects.imarket.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
@EqualsAndHashCode(callSuper = true)
public class Group extends BaseEntity {

    @Column(name = "groupname", nullable = false, unique = true)
    private String groupName;

    // TODO add @ManyToOne category reference

}
