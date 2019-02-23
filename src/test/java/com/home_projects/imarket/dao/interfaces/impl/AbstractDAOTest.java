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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Transactional
@SpringBootTest
@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
public class AbstractDAOTest {

    @Autowired private AuthorizedUserService authorizedUserService;
    @PersistenceContext private EntityManager entityManager;
    private static final Long FIRST_ID = 1L;
    private static final Long SECOND_ID = 2L;
    private static final String FIRST_TEST_NAME = "FirstTestName";
    private static final String SECOND_TEST_NAME = "SecondTestName";
    private static final String FIRST_TEST_PASSWORD = "PASSWORD1";
    private static final String SECOND_TEST_PASSWORD = "PASSWORD2";

    @Before
    public void setUp() throws Exception {
        entityManager.createNativeQuery("INSERT INTO authorized_users (id, username, password) " +
                                           "VALUES (" + FIRST_ID + ", '"
                                                      + FIRST_TEST_NAME +"', '"
                                                      + FIRST_TEST_PASSWORD + "'), ("
                                                      + SECOND_ID + ", '"
                                                      + SECOND_TEST_NAME + "', '"
                                                      + SECOND_TEST_PASSWORD + "')")
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
    public void check_of_getting_several_entities_by_ids() {
        final int EXPECTED_LIST_SIZE = 2;
        List<AuthorizedUser> actualUsers = authorizedUserService.getAll(Arrays.asList(1L, 2L));
        assertNotNull(actualUsers);
        assertEquals(EXPECTED_LIST_SIZE, actualUsers.size());
        AuthorizedUser firstUser = new AuthorizedUser();
        firstUser.setId(FIRST_ID);
        firstUser.setUserName(FIRST_TEST_NAME);
        firstUser.setPassword(FIRST_TEST_PASSWORD);

        AuthorizedUser secondUser = new AuthorizedUser();
        secondUser.setId(SECOND_ID);
        secondUser.setUserName(SECOND_TEST_NAME);
        secondUser.setPassword(SECOND_TEST_PASSWORD);
        List<AuthorizedUser> expectedUsers = Arrays.asList(firstUser, secondUser);
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteAll() {
    }
}