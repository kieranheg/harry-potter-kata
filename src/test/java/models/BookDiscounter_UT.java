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
        
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
    
        HashMap<String, Integer> bookCounters = discountCalculator.getItemCounters();

        assertEquals(4, bookCounters.size());
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
    
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        discountCalculator.calculateIndividualItemCounts(books);
    
        HashMap<String, Integer> bookCounters = discountCalculator.getItemCounters();
    
        assertEquals(new Integer(1), bookCounters.get("ABC"));
        assertEquals(new Integer(2), bookCounters.get("DEF"));
        assertEquals(new Integer(2), bookCounters.get("GHI"));
        assertEquals(new Integer(1), bookCounters.get("XYZ"));
    }
    
    @Test
    public void getDiscountApplicableToUniqueBookCount() {
        DiscountCalculator discountCalculator = new DiscountCalculator();
        assertEquals(0, discountCalculator.getDiscountForNumUniqueItems(1));
        assertEquals(5, discountCalculator.getDiscountForNumUniqueItems(2));
        assertEquals(10, discountCalculator.getDiscountForNumUniqueItems(3));
        assertEquals(20, discountCalculator.getDiscountForNumUniqueItems(4));
        assertEquals(25, discountCalculator.getDiscountForNumUniqueItems(5));
    }
    
    @Test
    public void calculateCostOfOneBook() {
        List<Book> books = asList(new Book("ABC"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(8.00, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfTwoUniqueBooks() {
        List<Book> books = asList(new Book("ABC"),
                new Book("DEF"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(15.20, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfThreeUniqueBooks() {
        List<Book> books = asList(new Book("ABC"),
                new Book("DEF"),
                new Book("GHI"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(21.60, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfFiveUniqueBooks() {
        List<Book> books = asList(new Book("ABC"),
                new Book("DEF"),
                new Book("GHI"),
                new Book("JKL"),
                new Book("MNO"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(30.00, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfTheSameTwoBooks() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(16.00, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfTheSameThreeBooks() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(24.00, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfTheSameFourBooks() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(32.00, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfTheSameFiveBooks() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(40.00, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfThreeBooksWithTwoTheSame() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"),
                new Book("DEF"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(23.20, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfFourBooksWithTwoDifferent() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"),
                new Book("DEF"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(31.20, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfFiveBooksWithThreeDifferentAndThreeCopiesOfOne() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"),
                new Book("ABC"),
                new Book("DEF"),
                new Book("GHI"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(37.60, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfFiveBooksWithThreeDifferentAndTwoCopiesOfTwo() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"),
                new Book("DEF"),
                new Book("DEF"),
                new Book("GHI"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(37.60, priceOfBooks, 0);
    }
    
    @Test
    public void calculateCostOfTheBooksCombo() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"),
                new Book("DEF"),
                new Book("DEF"),
                new Book("GHI"),
                new Book("GHI"),
                new Book("JKL"),
                new Book("MNO"));
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.setItemsToDiscount(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost(books);
        assertEquals(51.60, priceOfBooks, 0);
    }
}
