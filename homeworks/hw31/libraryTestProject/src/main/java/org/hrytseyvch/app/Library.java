package org.hrytseyvch.app;

import java.util.ArrayList;
import java.util.List;

public class Library {

    //    private List<Book> allBooks;
    //    public Library(){
//            allBooks = new ArrayList<>();
//    }
//
//    public Library(List<Book> allBooks) {
//        this.allBooks = allBooks;
//    }

    private final List<Book> allBooks = new ArrayList<>();

    public void addBook(Book book) {
        if (book != null)
            allBooks.add(book);
        else {
            throw new IllegalArgumentException("Book cannot be null");
        }
    }

    public boolean removeBook(Book book) {
        if (book != null)
            if (allBooks.contains(book)) {
                allBooks.remove(book);
                return true;
            }
        return false;
    }

    public List<Book> getBooks() {
        return allBooks;
    }

    public int getBookCount() {
        return allBooks.size();
    }
}
