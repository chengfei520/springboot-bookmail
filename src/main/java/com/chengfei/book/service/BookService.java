package com.chengfei.book.service;

import com.chengfei.book.pojo.Book;
import com.chengfei.book.pojo.Page;

import java.util.List;

public interface BookService {
    public List<Book> queryBooks();
    public void deleteBookById(Integer id);
    public Book queryBookById(Integer id);
    public void alterBook(Book book);
    public void saveBook(Book book);

    Page<Book> page(Integer pageNo, Integer pageSize);
}
