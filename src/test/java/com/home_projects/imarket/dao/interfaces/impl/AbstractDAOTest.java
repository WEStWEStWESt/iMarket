package com.home_projects.imarket.dao.interfaces.impl;

import com.home_projects.imarket.models.user.AuthorizedUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class AbstractDAOTest {

    @Autowired private AuthorizedUserService authorizedUserService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void check_of_entity_creation() {
        AuthorizedUser actualUser = authorizedUserService.create();
        assertNotNull(actualUser);

        assertTrue(actualUser instanceof AuthorizedUser);

        AuthorizedUser expectedUser = new AuthorizedUser();
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getOne() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteAll() {
    }
}