package com.greatbit.bookstore.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BooksStorage {

    private static Set<Book> books = new HashSet<>();

     static {
        books.add(new Book(
                UUID.randomUUID().toString(),
                "Учение Дона Хуана",
                "Карлос Кастанеда",
                430)
        );
        books.add(new Book(
                UUID.randomUUID().toString(),
                "Богатый Папа, Бедный Папа",
                "Роберт Киосаки",
                320));
    }
    public static Set<Book> getBooks() {return books;}
}
