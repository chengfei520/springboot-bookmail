package com.chengfei.book.dao;

import com.chengfei.book.mapper.BookMapper;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookDaoTest {
    @Autowired
    BookMapper bookMapper;
    @Test
    void queryBooks(){
        System.out.println(bookMapper.queryBooks());
    }
    @Test
    void deleteBook(){
        bookMapper.deleteBookById(20);
    }
    @Test
    void queryBookCount(){
        System.out.println(bookMapper.queryForPageTotalCount());
    }
    @Test
    void queryPageBookItems(){
        System.out.println(bookMapper.queryForPageItems(4,4));
    }
}
