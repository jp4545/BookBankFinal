package com.example.jp.myapplication;

public class RequestBooks {
    private String bookname;
    private String author;
    private String edition;
    private String publisher;

    private RequestBooks()
    {

    }

    public RequestBooks(String bookname, String author, String edition, String publisher) {
        this.bookname = bookname;
        this.author = author;
        this.edition = edition;
        this.publisher = publisher;
    }

    public String getBookname() {
        return bookname;
    }

    public String getAuthor() {
        return author;
    }

    public String getEdition() {
        return edition;
    }

    public String getPublisher() {
        return publisher;
    }
}
