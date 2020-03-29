package com.yatsyshyn.JakartaEE06HW;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryService extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b " +
            "WHERE b.title LIKE :title ")
    List<Book> getByTitle(@Param("title") String title);

    @Query("SELECT b FROM Book b " +
            "WHERE b.author LIKE :author ")
    List<Book> getByAuthor(@Param("author") String author);

    @Query("SELECT b FROM Book b " +
            "WHERE b.isbn LIKE :isbn ")
    List<Book> getByIsbn(@Param("isbn") String isbn);
}