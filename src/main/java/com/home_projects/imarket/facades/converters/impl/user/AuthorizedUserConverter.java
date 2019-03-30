package com.home_projects.imarket.facades.converters.impl.user;

import com.home_projects.imarket.facades.converters.AbstractConverter;
import com.home_projects.imarket.facades.dto.user.AuthorizedUserDTO;
import com.home_projects.imarket.models.user.AuthorizedUser;
import org.springframework.stereotype.Component;

@Component
public class AuthorizedUserConverter extends AbstractConverter<AuthorizedUserDTO, AuthorizedUser> {
    @Override
    public AuthorizedUser convert(AuthorizedUserDTO source, AuthorizedUser target) {
        target.setId(source.getId());

        setIfPresent(source.getUserName(), target::setUserName);
        setIfPresent(source.getPassword(), target::setPassword);

        return target;
    }
}
