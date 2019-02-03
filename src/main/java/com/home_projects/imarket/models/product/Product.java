package com.home_projects.imarket.models.product;

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
@Table(name = "products")
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {

    @Column(name = "productname", nullable = false, unique = true)
    private String productName;

    @Column(name = "description_short")
    private String descriptionShort;

    @Column(name = "description_full")
    private String descriptionFull;

    private Long price;

    private String picture;

    @Column(name = "store_status", length = 6)
    private String storeStatus;

    private Float discount;

    // TODO add @ManyToOne group reference
}
