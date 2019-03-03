package com.home_projects.imarket.facades.dto.user;

import com.home_projects.imarket.facades.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorizedUserDTO extends BaseDTO {

    private String userName;
    private String password;
}
