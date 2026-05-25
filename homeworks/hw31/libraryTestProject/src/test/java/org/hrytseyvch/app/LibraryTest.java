package org.hrytseyvch.app;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library lib;
    @BeforeEach
    void init(){
        lib = new Library();
    }

    @Test
    void testAddBookCorrectly() {
        Book book = new Book("Test book", "JJ");
        lib.addBook(book);
        assertTrue(lib.getBooks().contains(book));
        assertEquals(1, lib.getBookCount());
    }

    @Test
    void testAddBook_Null_ShouldNotAdd() {
        lib.addBook(null);

        assertEquals(0, lib.getBookCount()); // Кількість має лишитись 0
        assertTrue(lib.getBooks().isEmpty());
    }

    @Test
    void testRemoveBookCorrectly() {
        Book book = new Book("Test book", "JJ");
        Book book2 = new Book("Test book2", "JJ2");
        lib.addBook(book);
        lib.addBook(book2);
        assertTrue(lib.getBooks().contains(book));
        assertTrue(lib.getBooks().contains(book2));
        assertTrue(lib.removeBook(book));
        assertFalse(lib.getBooks().contains(book));
        assertEquals(1, lib.getBookCount());
    }

    @Test
    void testRemoveBookNonExisting() {
        Book bookInLibrary = new Book("Book 1", "Author 1");
        Book bookNotInLibrary = new Book("Book 2", "Author 2");

        lib.addBook(bookInLibrary);
        assertEquals(1, lib.getBookCount());
        assertFalse(lib.removeBook(bookNotInLibrary));
        assertEquals(1, lib.getBookCount()); //юез змін
    }

    @Test
    void testRemoveBookNull() {
        Book book = new Book("Test book", "ОО");
        lib.addBook(book);
        assertFalse(lib.removeBook(null));
        assertEquals(1, lib.getBookCount());
    }

    @Test
    void testGetBooks() {
        Book book1 = new Book("Book 1", "Author 1");
        Book book2 = new Book("Book 2", "Author 2");
        lib.addBook(book1);
        lib.addBook(book2);
        List<Book> books = lib.getBooks();
        assertEquals(2, books.size());
        assertTrue(books.contains(book1) && books.contains(book2)); //так ж можна?
    }

    @Test
    void testGetBookCount() {
        assertEquals(0, lib.getBookCount(), "Initial count should be 0");

        lib.addBook(new Book("Book 1", "Author 1"));
        assertEquals(1, lib.getBookCount());
        lib.addBook(new Book("Book 2", "Author 2"));
        assertEquals(2, lib.getBookCount());
    }
}