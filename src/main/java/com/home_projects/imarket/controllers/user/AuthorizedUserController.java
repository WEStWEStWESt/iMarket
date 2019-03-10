package com.home_projects.imarket.controllers.user;

import com.home_projects.imarket.facades.dto.user.AuthorizedUserDTO;
import com.home_projects.imarket.models.user.AuthorizedUser;
import com.home_projects.imarket.services.ConverterService;
import com.home_projects.imarket.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AuthorizedUserController {

    @Autowired
    private ConverterService converterService;
    @Autowired
    private ModelService modelService;

    @GetMapping("{id}")
    public AuthorizedUserDTO getOne(@PathVariable Long id) {
        return converterService.getOne(id, AuthorizedUserDTO.class, AuthorizedUser.class);
    }

    @PostMapping
    public List<AuthorizedUserDTO> getAll(@RequestBody List<Long> ids) {
        return converterService.getAll(ids, AuthorizedUserDTO.class, AuthorizedUser.class);
    }

    @PutMapping
    public AuthorizedUserDTO save(@RequestBody AuthorizedUserDTO dto) {
        return converterService.save(dto, AuthorizedUserDTO.class, AuthorizedUser.class);
    }

    @PutMapping("/")
    public List<AuthorizedUserDTO> saveAll(@RequestBody List<AuthorizedUserDTO> dtos) {
        return converterService.saveAll(dtos, AuthorizedUserDTO.class, AuthorizedUser.class);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Long id) {
        return modelService.delete(AuthorizedUser.class, id);
    }

    @DeleteMapping
    public boolean delete(@RequestBody AuthorizedUserDTO dto) {
        return converterService.delete(dto, AuthorizedUserDTO.class, AuthorizedUser.class);
    }

    @DeleteMapping("/")
    public boolean deleteAll(@RequestBody List<Long> ids) {
        return modelService.deleteAll(AuthorizedUser.class, ids);
    }

    @DeleteMapping("/all")
    public boolean deleteAll() {
        return modelService.deleteAll(AuthorizedUser.class);
    }
}
