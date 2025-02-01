package com.greatbit.bookstore.model;



public class Book {
    private String id;
    private Integer pages;
    private String name;
    private String author;

    public Book() {
    }



    public Book(String id, int pages, String name, String author) {
        this.id = id;
        this.pages = pages;
        this.name = name;
        this.author = author;
    }

    public Book(int pages, String name, String author) {
        this.pages = pages;
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", pages=" + pages +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
