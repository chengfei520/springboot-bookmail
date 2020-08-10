package com.chengfei.book.controller;

import com.chengfei.book.pojo.Book;
import com.chengfei.book.pojo.Page;
import com.chengfei.book.service.BookService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    /**
     * 查询所有图书并返回到book_manager页面
     * @param model
     * @return
     */
    @GetMapping("/manager/book_manager")
    public String showBooks(Model model){
        List<Book> books = bookService.queryBooks();
        model.addAttribute("books",books);
        return "forward:/book_manager.html";
    }

    /**
     * 删除图书
     * @param id
     * @return
     */
    @DeleteMapping("/manager/book/{id}")
    public String deleteBook(@PathVariable("id") Integer id){
        bookService.deleteBookById(id);
        return "redirect:/manager/book_manager";
    }

    /**
     * 修改图书时，先查询到这个图书，再回显信息到页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/manager/book/{id}")
    public String queryBook(@PathVariable("id") Integer id,Model model){
        Book book = bookService.queryBookById(id);
        model.addAttribute("book",book);
        return "forward:/book_edit.html";
    }

    /**
     * 修改图书的第二步，修改信息并更新到数据库
     * @param id
     * @param name
     * @param price
     * @param author
     * @param sales
     * @param stock
     * @return
     */
    @PutMapping("/manager/book")
    public String updateBook(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("price") BigDecimal price,
                             @RequestParam("author") String author,
                             @RequestParam("sales") Integer sales,
                             @RequestParam("stock") Integer stock){
        bookService.alterBook(new Book(id,name,author,price,sales,stock,null));
        return "redirect:/manager/book_manager";
    }

    /**
     * 添加图书并保存到数据库
     * @param name
     * @param price
     * @param author
     * @param sales
     * @param stock
     * @return
     */
    @PostMapping("/manager/book")
    public String addBook(@RequestParam("name") String name,
                          @RequestParam("price") BigDecimal price,
                          @RequestParam("author") String author,
                          @RequestParam("sales") Integer sales,
                          @RequestParam("stock") Integer stock){
        bookService.saveBook(new Book(null,name,author,price,sales,stock,null));
        return "redirect:/manager/book_manager";
    }

    /**
     * 图书分页
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/manager/book_page")
    public String page(@RequestParam("pageNo") Integer pageNo,
                       @RequestParam("pageSize") Integer pageSize,Model model){
        Page<Book> page=bookService.page(pageNo,pageSize);
        model.addAttribute("page",page);
        return "forward:/book_manager.html";
    }

    /**
     * 主页商品的分页显示
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String pageIndex(@RequestParam("pageNo") Integer pageNo,
                       @RequestParam("pageSize") Integer pageSize,Model model){
           Page<Book> page = bookService.page(pageNo, pageSize);
           page.setUrl("/index?");
           model.addAttribute("page", page);
        return "forward:/index.html";
    }
    @GetMapping("/indexPrice")
    public String pageIndexByPrice(@RequestParam("pageNo") Integer pageNo,
                            @RequestParam("pageSize") Integer pageSize,@RequestParam("min") Integer min,
                                   @RequestParam("max") Integer max,Model model){
            Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);
            page.setUrl("/indexPrice?min="+min+"&max="+max+"&");
            model.addAttribute("page", page);
            model.addAttribute("min",min);
            model.addAttribute("max",max);
        return "forward:/index.html";
    }

}
