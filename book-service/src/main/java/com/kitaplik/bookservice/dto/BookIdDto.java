package com.kitaplik.bookservice.dto;

public record BookIdDto(
        String bookId, String isbn) {


    public static BookIdDto convert(String bookId, String isbn) {
        return new BookIdDto(bookId, isbn);
    }
}