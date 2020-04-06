package com.yatsyshyn.JakartaEE06HW.dto;

import com.yatsyshyn.JakartaEE06HW.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class ResponseDto {
    private final List<Book> books;
}
