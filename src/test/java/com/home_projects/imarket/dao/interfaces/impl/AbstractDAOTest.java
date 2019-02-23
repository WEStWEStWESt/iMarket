package com.home_projects.imarket.dao.interfaces.impl;

import com.home_projects.imarket.models.user.AuthorizedUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class AbstractDAOTest {

    @Autowired private AuthorizedUserService authorizedUserService;
    @PersistenceContext private EntityManager entityManager;
    private static final Long FIRST_ID = 1L;
    private static final String FIRST_TEST_NAME = "FirstTestName";
    private static final String FIRST_TEST_PASSWORD = "PASSWORD1";

    @Before
    public void setUp() throws Exception {
        entityManager.createNativeQuery("INSERT INTO authorized_users (id, username, password) " +
                                           "VALUES (" + FIRST_ID + ", '"
                                                      + FIRST_TEST_NAME +"', '"
                                                      + FIRST_TEST_PASSWORD + "')")
                     .executeUpdate();
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
    public void check_of_getting_entity_by_id() {
        AuthorizedUser actualUser = authorizedUserService.getOne(FIRST_ID);
        assertNotNull(actualUser);

        AuthorizedUser expectedUser = new AuthorizedUser();
        expectedUser.setId(FIRST_ID);
        expectedUser.setUserName(FIRST_TEST_NAME);
        expectedUser.setPassword(FIRST_TEST_PASSWORD);

        assertEquals(expectedUser, actualUser);
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