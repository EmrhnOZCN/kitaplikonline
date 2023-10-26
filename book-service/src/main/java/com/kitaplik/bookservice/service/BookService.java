package com.kitaplik.bookservice.service;

import com.kitaplik.bookservice.dto.BookDto;
import com.kitaplik.bookservice.dto.BookIdDto;
import com.kitaplik.bookservice.exception.BookNotFoundException;
import com.kitaplik.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {


    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //.stream() metodu, bu kitapları bir akışa dönüştürür.
    //.map(BookDto::convert) ifadesi, her kitap nesnesini BookDto türüne dönüştürür.
    // BookDto::convert belirtilen metodu kullanarak dönüşümü gerçekleştirir.
    //collect(Collectors.toList()) ifadesi, akıştaki tüm dönüştürülmüş kitapları bir liste olarak toplar ve bu listeyi döndürür.

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDto::convert)
                .collect(Collectors.toList());
    }


    public BookIdDto findByIsbn(String isbn){

        return bookRepository.findBookByIsbn(isbn)
                .map(book -> new BookIdDto(book.getId(), book.getIsbn()))
                .orElseThrow(() -> new BookNotFoundException("Book could not found by isbn:  " + isbn));



    }


    public BookDto findBookDetailsById(String id) {
        return bookRepository.findById(id)
                .map(BookDto::convert)
                .orElseThrow(() -> new BookNotFoundException("Book could not found by id:" + id));
    }
}
