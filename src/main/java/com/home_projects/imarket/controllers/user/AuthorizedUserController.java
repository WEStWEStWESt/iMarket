package com.home_projects.imarket.controllers.user;

import com.home_projects.imarket.facades.dto.user.AuthorizedUserDTO;
import com.home_projects.imarket.facades.impl.user.AuthorizedUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AuthorizedUserController {
    @Autowired
    private AuthorizedUserFacade facade;

    @GetMapping("{id}")
    public AuthorizedUserDTO getOne(@PathVariable Long id) {
        return facade.getOne(id);
    }

    @PostMapping
    public List<AuthorizedUserDTO> getAll(@RequestBody List<Long> ids) {
        return facade.getAll(ids);
    }

    @PutMapping
    public AuthorizedUserDTO save(@RequestBody AuthorizedUserDTO dto) {
        return facade.save(dto);
    }

    @PutMapping("/")
    public List<AuthorizedUserDTO> saveAll(@RequestBody List<AuthorizedUserDTO> dtos) {
        return facade.saveAll(dtos);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Long id) {
        return facade.delete(id);
    }

    @DeleteMapping
    public boolean delete(@RequestBody AuthorizedUserDTO dto) {
        return facade.delete(dto);
    }

    @DeleteMapping("/")
    public boolean deleteAll(@RequestBody List<Long> ids) {
        return facade.deleteAll(ids);
    }

    @DeleteMapping("/all")
    public boolean deleteAll() {
        return facade.deleteAll();
    }
}
