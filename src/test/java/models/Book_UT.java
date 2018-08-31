package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Book_UT {
    
    @Test
    public void BookHasATitleAndPrice() {
        Book book = new Book("Book1");
        assertEquals("Book1", book.getTitle());
        assertEquals(8.00, book.getPrice(), 0);
    }
    
}
