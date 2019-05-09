package com.home_projects.imarket.services.view;

import com.home_projects.imarket.models.view.Field;
import com.home_projects.imarket.models.view.JoinViewTable;
import com.home_projects.imarket.models.view.MainViewTable;
import com.home_projects.imarket.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

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

    @Test
    public void check_of_select_part_with_empty_content(){
        Query query = CustomQuery.init();
        String ACTUAL = query.select(Collections.emptyList()).toString();
        String EXPECTED = StringUtils.getEmpty();
        assertThat(ACTUAL, is(equalTo(EXPECTED)));
    }

    @Test
    public void check_of_select_part_with_invalid_content(){
        Query query = CustomQuery.init();
        String ACTUAL = query.select(Collections.singletonList(new Field())).toString();
        String EXPECTED = StringUtils.getEmpty();
        assertThat(ACTUAL, is(equalTo(EXPECTED)));
    }

    @Test
    public void check_of_select_part_with_content(){
        Query query = CustomQuery.init();
        String ACTUAL = query.select(Collections.singletonList(
                Field
                        .builder()
                        .fieldName("field")
                        .mainTable(MainViewTable.builder().mainTableName("main").build())
                        .fieldNumber(1)
                        .build()))
                .toString();
        String EXPECTED = "SELECT field";
        assertThat(ACTUAL, is(equalTo(EXPECTED)));
    }

    @Test
    public void check_of_select_part_with_multiple_content(){
        Query query = CustomQuery.init();
        MainViewTable main = MainViewTable.builder().mainTableName("main").build();
        String ACTUAL = query.select(Arrays.asList(
                Field
                        .builder()
                        .fieldName("field1")
                        .mainTable(main)
                        .fieldNumber(1)
                        .build(),
                Field
                        .builder()
                        .fieldName("field2")
                        .mainTable(main)
                        .fieldNumber(2)
                        .build(),
                Field
                        .builder()
                        .fieldName("field3")
                        .mainTable(main)
                        .fieldNumber(3)
                        .build()))
                .toString();
        String EXPECTED = "SELECT field1, field2, field3";
        assertThat(ACTUAL, is(equalTo(EXPECTED)));
    }

    @Test
    public void check_of_select_part_with_multiple_content_and_aliases(){
        Query query = CustomQuery.init();
        MainViewTable main = MainViewTable.builder().mainTableName("main").build();
        String ACTUAL = query.select(Arrays.asList(
                Field
                        .builder()
                        .fieldName("field1")
                        .mainTable(main)
                        .fieldNumber(1)
                        .build(),
                Field
                        .builder()
                        .fieldName("field2")
                        .mainTable(main)
                        .fieldNumber(2)
                        .build(),
                Field
                        .builder()
                        .fieldName("field3")
                        .mainTable(main)
                        .fieldNumber(3)
                        .build()))
                .add(QueryPartType.SELECT, "m")
                .add(QueryPartType.SELECT, Arrays.asList(
                        Field
                                .builder()
                                .fieldName("field1")
                                .joinTable(JoinViewTable.builder().joinTableName("join").build())
                                .fieldNumber(4)
                                .joined(true)
                                .build(),
                        Field
                                .builder()
                                .fieldName("field2")
                                .joinTable(JoinViewTable.builder().joinTableName("join").build())
                                .fieldNumber(5)
                                .joined(true)
                                .build()
                ), "j")
                .toString();
        String EXPECTED = "SELECT m.field1, m.field2, m.field3, j.field1, j.field2";
        assertThat(ACTUAL, is(equalTo(EXPECTED)));
    }

}