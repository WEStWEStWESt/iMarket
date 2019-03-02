package com.home_projects.imarket.models.cart;

import com.home_projects.imarket.models.BaseEntity;
import com.home_projects.imarket.models.cart.enums.DeliveryType;
import com.home_projects.imarket.models.cart.enums.PaymentType;
import com.home_projects.imarket.models.product.Product;
import com.home_projects.imarket.models.profile.Profile;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
@EqualsAndHashCode(callSuper = true)
public class Cart extends BaseEntity {

    @Column(length = 4)
    @Enumerated(EnumType.STRING)
    private PaymentType payment;

    @Column(length = 7)
    @Enumerated(EnumType.STRING)
    private DeliveryType delivery;

    @Column(name = "opened_date")
    private Timestamp openedDate;

    @Column(name = "closed_date")
    private Timestamp closedDate;

    @Column(name = "paid_date")
    private Timestamp paidDate;

    @Column(name = "sent_date")
    private Timestamp sentDate;

    @Column(name = "got_date")
    private Timestamp gotDate;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(name = "carts_products",
               joinColumns = @JoinColumn(name = "cart_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;
}
