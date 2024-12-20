package com.kabanchiki.core;

public class Book {
    private String title;
    private String author;
    private int pages;

    private Book(BookBuilder bookBuilder){
        this.title = bookBuilder.title;
        this.author = bookBuilder.author;
        this.pages = bookBuilder.pages;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getPages() {
        return pages;
    }

    public static class BookBuilder{
        private String title;
        private String author;
        private int pages;

        public BookBuilder(String title) {
            this.title = title;
        }

        public BookBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }
        public BookBuilder setPages(int pages) {
            this.pages = pages;
            return this;
        }

        public Book build(){
            return new Book(this);
        }
    }
}
