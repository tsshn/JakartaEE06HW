package com.yatsyshyn.JakartaEE06HW;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MainController {

    private BookService bookService;

    @Autowired
    public MainController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping({"/", ""})
    public String library(Model model) {
        List<Book> library = bookService.getAll();
        model.addAttribute("library", library);
        return "library";
    }

    @RequestMapping(value = "/book/{id}")
    public String details(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        return "details";
    }

    @RequestMapping(value = "/redirectAddBook", method = RequestMethod.GET)
    public String redirectAddBook() {
        return "addBook";
    }

    @RequestMapping(value = "/addBook", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveBook(@ModelAttribute Book book, Model model) {
        model.addAttribute("book", book);
        bookService.add(book.getTitle(), book.getAuthor(), book.getIsbn());
        return "redirect:/";
    }

}