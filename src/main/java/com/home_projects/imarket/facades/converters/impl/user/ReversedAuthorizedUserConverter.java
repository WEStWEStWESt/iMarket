package com.home_projects.imarket.facades.converters.impl.user;

import com.home_projects.imarket.facades.converters.AbstractConverter;
import com.home_projects.imarket.facades.dto.user.AuthorizedUserDTO;
import com.home_projects.imarket.models.user.AuthorizedUser;
import org.springframework.stereotype.Component;

@Component
public class ReversedAuthorizedUserConverter extends AbstractConverter<AuthorizedUser, AuthorizedUserDTO> {
    @Override
    public AuthorizedUserDTO convert(AuthorizedUser source, AuthorizedUserDTO target) {
        target.setId(source.getId());
        target.setUserName(source.getUserName());
        target.setPassword(source.getPassword());
        return target;
    }
}
