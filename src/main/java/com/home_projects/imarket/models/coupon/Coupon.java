package com.home_projects.imarket.models.coupon;

import com.home_projects.imarket.models.BaseEntity;
import com.home_projects.imarket.models.profile.Profile;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coupons")
@EqualsAndHashCode(callSuper = true)
public class Coupon extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String description;

    private Long sum;

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(name = "profiles_coupons",
               joinColumns = @JoinColumn(name = "profile_id"),
               inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    private Set<Profile> profiles;
}
