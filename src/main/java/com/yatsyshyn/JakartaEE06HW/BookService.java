package com.yatsyshyn.JakartaEE06HW;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private RepositoryService repositoryService;

    @Transactional
    public Book add(String title, String author, String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        return repositoryService.saveAndFlush(book);
    }

    @Transactional
    public List<Book> getAll() {
        return repositoryService.findAll();
    }

    @Transactional
    public Book getById(int id) {
        Optional<Book> book = repositoryService.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public List<Book> getByTitle(String title) {
        return repositoryService.getByTitle('%' + title + '%');
    }

    @Transactional
    public List<Book> getByAuthor(String author) {
        return repositoryService.getByAuthor('%' + author + '%');
    }

    @Transactional
    public List<Book> getByIsbn(String isbn) {
        return repositoryService.getByIsbn('%' + isbn + '%');
    }

}
