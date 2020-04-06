package com.yatsyshyn.JakartaEE06HW;

import com.yatsyshyn.JakartaEE06HW.dto.FilterDto;
import com.yatsyshyn.JakartaEE06HW.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookServiceController {
    private final BookService bookService;

    @ResponseBody
    @GetMapping("/get")
    public ResponseEntity<ResponseDto> bookList() {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.of(bookService.getAll()));
    }

    @ResponseBody
    @PostMapping("/filter")
    public ResponseEntity<ResponseDto> findBook(@RequestBody FilterDto dto) {
        String input = dto.getInput();
        String property = dto.getProperty();
        List<Book> books;

        if (input.equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.of(null));
        }

        switch (property) {
            case "title": {
                books = bookService.getByTitle(input);
                break;
            }
            case "author": {
                books = bookService.getByAuthor(input);
                break;
            }
            case "isbn": {
                books = bookService.getByIsbn(input);
                break;
            }
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.of(null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.of(books));
    }

    @ResponseBody
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addBook(@RequestBody Book book) {
        if (!book.getAuthor().isEmpty() && !book.getTitle().isEmpty() && !book.getIsbn().isEmpty()) {
            bookService.add(book.getTitle(), book.getAuthor(), book.getIsbn());
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.of(bookService.getAll()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.of(null));
        }
    }
}
