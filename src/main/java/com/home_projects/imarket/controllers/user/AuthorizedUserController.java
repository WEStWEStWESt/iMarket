package com.home_projects.imarket.controllers.user;

import com.home_projects.imarket.facades.dto.user.AuthorizedUserDTO;
import com.home_projects.imarket.models.user.AuthorizedUser;
import com.home_projects.imarket.services.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AuthorizedUserController {

    @Autowired
    private ConverterService converterService;

    @GetMapping("/{id}")
    public AuthorizedUserDTO getOne(@PathVariable Long id) {
        return converterService.getOne(id, AuthorizedUserDTO.class, AuthorizedUser.class);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AuthorizedUserDTO> getAll(@RequestBody List<Long> ids) {
        return converterService.getAll(ids, AuthorizedUserDTO.class, AuthorizedUser.class);
    }
}
