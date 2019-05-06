package com.home_projects.imarket.services.view;

import com.home_projects.imarket.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomQueryTest {

    @Test
    public void check_of_empty_select_part() {
        Query query = CustomQuery.init();
        String ACTUAL = query.select(false).toString();
        String EXPECTED = StringUtils.getEmpty();
        assertThat(ACTUAL, is(equalTo(EXPECTED)));
    }

    @Test
    public void check_of_select_part_with_count(){
        Query query = CustomQuery.init();
        String ACTUAL = query.select(true).toString();
        String EXPECTED = "SELECT count(*)";
        assertThat(ACTUAL, is(equalTo(EXPECTED)));
    }

    @Test
    public void check_of_select_part_with_count_and_alias(){
        Query query = CustomQuery.init();
        String ACTUAL = query.select(true).add(QueryPartType.SELECT, "t").toString();
        String EXPECTED = "SELECT count(t)";
        assertThat(ACTUAL, is(equalTo(EXPECTED)));
    }

    @Test
    public void check_of_select_part_with_count_and_nullable_alias(){
        Query query = CustomQuery.init();
        String ACTUAL = query.select(true).add(QueryPartType.SELECT, null).toString();
        String EXPECTED = "SELECT count(*)";
        assertThat(ACTUAL, is(equalTo(EXPECTED)));
    }

}