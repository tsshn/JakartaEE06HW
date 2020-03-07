package com.yatsyshyn.JakartaEE06HW;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private EntityManager entityManager;

    @Autowired
    public BookService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void add(String title, String author, String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        entityManager.merge(book);
    }

    @Transactional
    public List<Book> getAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Transactional
    public Book getById(int id) {
        return entityManager.find(Book.class, id);
    }

    @Transactional
    public List<Book> getByTitle(String title) {
        return entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.title = :title", Book.class)
                .setParameter("title", title).getResultList();
    }

    @Transactional
    public List<Book> getByAuthor(String author) {
        return entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.author = :author", Book.class)
                .setParameter("author", author).getResultList();
    }

    @Transactional
    public List<Book> getByISBN(String isbn) {
        return entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
                .setParameter("isbn", isbn).getResultList();
    }

}
