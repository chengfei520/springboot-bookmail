package com.chengfei.book.service.impl;

import com.chengfei.book.mapper.BookMapper;
import com.chengfei.book.pojo.Book;
import com.chengfei.book.pojo.Page;
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

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page<Book> page=new Page<>();
        //设置每页记录数
        page.setPageSize(pageSize);
        //计算和设置总记录数
        Integer pageTotalCount=bookMapper.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //设置总页数
        Integer pageTotal=pageTotalCount%pageSize==0?pageTotalCount/pageSize:(pageTotalCount/pageSize+1);
        page.setPageTotal(pageTotal);
        //设置当前页码
        page.setPageNo(pageNo);
        //设置当前页的开始
        int begin=(page.getPageNo()-1)*pageSize;
        //计算和设置当前页数据
        List<Book> items=bookMapper.queryForPageItems(begin,pageSize);
        page.setItems(items);
        return page;
    }
}
