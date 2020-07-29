package com.chengfei.book.mapper;

import com.chengfei.book.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    public List<Book> queryBooks();
    public void deleteBookById(Integer id);
    public Book queryBookById(Integer id);
    public void updateBook(Book book);
    public void saveBook(Book book );

    public Integer queryForPageTotalCount();

    public List<Book> queryForPageItems(Integer begin, Integer pageSize);
}
