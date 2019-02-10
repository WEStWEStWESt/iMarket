package com.home_projects.imarket.dao.interfaces;

import com.home_projects.imarket.models.user.AuthorizedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizedUserRepository extends JpaRepository<AuthorizedUser, Long> {

    AuthorizedUser findByUserName(String username);
    AuthorizedUser findByUserNameAndPassword(String username, String password);

}
