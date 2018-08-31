package models;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookDiscounter_UT {
    @Test
    public void buildBookerCounters() {
        List<Book> books = asList(new Book("ABC"), new Book("DEF"),
                new Book("GHI"), new Book("DEF"),
                new Book("XYZ"), new Book("GHI"));
        
        BookDiscounter bookDiscounter = new BookDiscounter();
        bookDiscounter.setBooksToDiscount(books);
    
        HashMap<String, Integer> bookCounters = bookDiscounter.getBookCounters();

        assertTrue(bookCounters.containsKey("ABC"));
        assertTrue(bookCounters.containsKey("DEF"));
        assertTrue(bookCounters.containsKey("GHI"));
        assertTrue(bookCounters.containsKey("XYZ"));
        assertEquals(new Integer(0), bookCounters.get("ABC"));
        assertEquals(new Integer(0), bookCounters.get("DEF"));
        assertEquals(new Integer(0), bookCounters.get("GHI"));
        assertEquals(new Integer(0), bookCounters.get("XYZ"));
    }
    
    @Test
    public void calculateIndividualBookCounts() {
        List<Book> books = asList(new Book("ABC"), new Book("DEF"),
                new Book("GHI"), new Book("DEF"),
                new Book("XYZ"), new Book("GHI"));
    
        BookDiscounter bookDiscounter = new BookDiscounter();
        bookDiscounter.setBooksToDiscount(books);
        bookDiscounter.calculateIndividualBookCounts(books);
    
        HashMap<String, Integer> bookCounters = bookDiscounter.getBookCounters();
    
        assertEquals(new Integer(1), bookCounters.get("ABC"));
        assertEquals(new Integer(2), bookCounters.get("DEF"));
        assertEquals(new Integer(2), bookCounters.get("GHI"));
        assertEquals(new Integer(1), bookCounters.get("XYZ"));
    }
    
    @Test
    public void getDiscountApplicableToBookCount() {
        BookDiscounter bookDiscounter = new BookDiscounter();
        assertEquals(0, bookDiscounter.getDiscountForBooksCount(1));
        assertEquals(5, bookDiscounter.getDiscountForBooksCount(2));
        assertEquals(10, bookDiscounter.getDiscountForBooksCount(3));
        assertEquals(20, bookDiscounter.getDiscountForBooksCount(4));
        assertEquals(25, bookDiscounter.getDiscountForBooksCount(5));
    }
    
    @Test
    public void calculateCostOfOneBook() {
        List<Book> books = asList(new Book("ABC"));
        BookDiscounter bookDiscounter = new BookDiscounter();
        bookDiscounter.setBooksToDiscount(books);
        final double priceOfBooks = bookDiscounter.calculateTotalCost(books);
        assertEquals(8.00, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfTwoBooks() {
        List<Book> books = asList(new Book("ABC"),
                new Book("DEF"));
        BookDiscounter bookDiscounter = new BookDiscounter();
        bookDiscounter.setBooksToDiscount(books);
        final double priceOfBooks = bookDiscounter.calculateTotalCost(books);
        assertEquals(16.00, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfTheSameTwoBooks() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"));
        BookDiscounter bookDiscounter = new BookDiscounter();
        bookDiscounter.setBooksToDiscount(books);
        final double priceOfBooks = bookDiscounter.calculateTotalCost(books);
        assertEquals(15.20, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfTheSameThreeBooks() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"));
        BookDiscounter bookDiscounter = new BookDiscounter();
        bookDiscounter.setBooksToDiscount(books);
        final double priceOfBooks = bookDiscounter.calculateTotalCost(books);
        assertEquals(21.60, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfTheSameFourBooks() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"));
        BookDiscounter bookDiscounter = new BookDiscounter();
        bookDiscounter.setBooksToDiscount(books);
        final double priceOfBooks = bookDiscounter.calculateTotalCost(books);
        assertEquals(25.60, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfTheSameFiveBooks() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"));
        BookDiscounter bookDiscounter = new BookDiscounter();
        bookDiscounter.setBooksToDiscount(books);
        final double priceOfBooks = bookDiscounter.calculateTotalCost(books);
        assertEquals(30.00, priceOfBooks, 0);
    }
    
//    @Test
//    public void calculateCostOfTheBooksCombo() {
//        List<Book> books = asList(new Book("ABC"),
//                new Book("ABC"),
//                new Book("DEF"),
//                new Book("DEF"),
//                new Book("GHI"),
//                new Book("GHI"),
//                new Book("JKL"),
//                new Book("MNO"));
//        BookDiscounter bookDiscounter = new BookDiscounter();
//        bookDiscounter.setBooksToDiscount(books);
//        final double priceOfBooks = bookDiscounter.calculateTotalCost(books);
//        assertEquals(51.60, priceOfBooks, 0);
//    }
}
