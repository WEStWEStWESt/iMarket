package com.home_projects.imarket.dao;

import com.home_projects.imarket.models.user.AuthorizedUser;
import com.home_projects.imarket.services.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Transactional
@SpringBootTest
@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@Sql({"/sql/auth_user_insert.sql"})
public class AbstractDAOTest {

    @Autowired
    private ModelService modelService;
    @PersistenceContext
    private EntityManager entityManager;
    private static final Long FIRST_ID = 2L;
    private static final Long SECOND_ID = 3L;
    private static final Long THIRD_ID = 4L;

    @Test
    public void check_of_entity_creation() {
        AuthorizedUser actualUser = modelService.create(AuthorizedUser.class);
        assertNotNull(actualUser);

        assertTrue(actualUser instanceof AuthorizedUser);

        AuthorizedUser expectedUser = new AuthorizedUser();
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void check_of_getting_entity_by_id() {
        AuthorizedUser actualUser = modelService.getOne(AuthorizedUser.class, FIRST_ID);
        assertNotNull(actualUser);

        AuthorizedUser expectedUser = entityManager.createQuery("FROM AuthorizedUser a WHERE a.id = " + FIRST_ID, AuthorizedUser.class)
                .getSingleResult();

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void check_of_getting_several_entities_by_ids() {
        final int EXPECTED_LIST_SIZE = 2;
        List<Long> ids = Arrays.asList(FIRST_ID, SECOND_ID);
        List<AuthorizedUser> actualUsers = modelService.getAll(AuthorizedUser.class, ids);
        assertNotNull(actualUsers);
        assertEquals(EXPECTED_LIST_SIZE, actualUsers.size());
        List<AuthorizedUser> expectedUsers = entityManager.createQuery("FROM AuthorizedUser a WHERE a.id IN (" +
                ids.stream()
                        .map(Objects::toString)
                        .collect(Collectors.joining(", ")) + ")")
                .getResultList();
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void check_of_deleting_entity_by_id() {
        assertTrue(modelService.delete(AuthorizedUser.class, FIRST_ID));

        final int EXPECTED_EXECUTION_RESULT = 0;
        assertEquals(EXPECTED_EXECUTION_RESULT, entityManager.createQuery("DELETE FROM AuthorizedUser a WHERE a.id = " + FIRST_ID)
                .executeUpdate());
    }

    @Test
    public void check_of_deleting_several_entities_by_ids() {
        List<Long> ids = Arrays.asList(FIRST_ID, SECOND_ID, THIRD_ID);
        assertTrue(modelService.deleteAll(AuthorizedUser.class, ids));

        final int EXPECTED_EXECUTION_RESULT = 0;
        assertEquals(EXPECTED_EXECUTION_RESULT, entityManager.createQuery(
                "DELETE FROM AuthorizedUser a WHERE a.id IN ("
                        + ids.stream()
                        .map(Objects::toString)
                        .collect(Collectors.joining(", ")) + ")")
                .executeUpdate());
    }
}