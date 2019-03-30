package com.home_projects.imarket.facades.interfaces;

import com.home_projects.imarket.facades.dto.BaseDTO;

import java.util.List;

public interface Facade<D extends BaseDTO> {
    D getOne(Long id);

    List<D> getAll(List<Long> ids);

    D save(D dto);

    List<D> saveAll(List<D> dtos);

    boolean delete(D dto);

    boolean delete(Long id);

    boolean deleteAll(List<Long> ids);

    boolean deleteAll();
}
