package com.home_projects.imarket.models.profile;

import com.home_projects.imarket.models.BaseEntity;
import com.home_projects.imarket.models.cart.Cart;
import com.home_projects.imarket.models.coupon.Coupon;
import com.home_projects.imarket.models.user.AuthorizedUser;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
@EqualsAndHashCode(callSuper = true)
public class Profile extends BaseEntity {

    @Column(name = "profile_name", length = 50, nullable = false)
    private String profileName;

    @Column(length = 50)
    private String email;

    @Column(length = 30)
    private String phone;

    private String address;

    /*call the command in postgresql console: CREATE EXTENSION IF NOT EXISTS pgcrypto */
    @Column(length = 20)
    @ColumnTransformer(read = "pgp_sym_decrypt(passport::bytea, 'crypt')",
                       write = "pgp_sym_encrypt(?, 'crypt')")
    private String passport;

    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "user_id")
    private AuthorizedUser user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "profiles")
    private Set<Coupon> coupons;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "profile")
    private Set<Cart> carts;

}
