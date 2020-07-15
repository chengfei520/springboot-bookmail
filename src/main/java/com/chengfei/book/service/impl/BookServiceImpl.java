package com.chengfei.book.service.impl;

import com.chengfei.book.mapper.BookMapper;
import com.chengfei.book.pojo.Book;
import com.chengfei.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Override
    public List<Book> queryBooks() {
        return bookMapper.queryBooks();
    }

    @Override
    public void deleteBookById(Integer id) {
        bookMapper.deleteBookById(id);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookMapper.queryBookById(id);
    }

    @Override
    public void alterBook(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public void saveBook(Book book) {
        bookMapper.saveBook(book);
    }
}
