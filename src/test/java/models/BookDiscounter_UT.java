package models;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookDiscounter_UT {
    @Test
    public void buildBookerCounters() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                putItemsInBasket("ABC", "DEF", "GHI", "DEF", "XYZ", "GHI"));
        
        Map<String, Integer> bookCounters = discountCalculator.getAllItemCounts();
        
        assertEquals(4, bookCounters.size());
        assertTrue(bookCounters.containsKey("ABC"));
        assertTrue(bookCounters.containsKey("DEF"));
        assertTrue(bookCounters.containsKey("GHI"));
        assertTrue(bookCounters.containsKey("XYZ"));
        assertEquals(new Integer(1), bookCounters.get("ABC"));
        assertEquals(new Integer(2), bookCounters.get("DEF"));
        assertEquals(new Integer(2), bookCounters.get("GHI"));
        assertEquals(new Integer(1), bookCounters.get("XYZ"));
    }

    @Test
    public void getDiscountApplicableToUniqueBookCount() {
        List<Book> books = emptyList();
        DiscountCalculator discountCalculator = new DiscountCalculator(books);
        assertEquals(0, discountCalculator.getDiscountForNumUniqueItems(1));
        assertEquals(5, discountCalculator.getDiscountForNumUniqueItems(2));
        assertEquals(10, discountCalculator.getDiscountForNumUniqueItems(3));
        assertEquals(20, discountCalculator.getDiscountForNumUniqueItems(4));
        assertEquals(25, discountCalculator.getDiscountForNumUniqueItems(5));
    }
//
//    @Test
//    public void calculateCostOfOneBook() {
//        List<Book> books = asList(new Book("ABC"));
//        DiscountCalculator discountCalculator = new DiscountCalculator(books);
//        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost();
//        assertEquals(8.00, priceOfBooks, 0);
//    }
//
//    @Test
//    public void calculateCostOfTwoUniqueBooks() {
//        List<Book> books = asList(new Book("ABC"),
//                new Book("DEF"));
//        DiscountCalculator discountCalculator = new DiscountCalculator(books);
//        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost();
//        assertEquals(15.20, priceOfBooks, 0);
//    }
//
//    @Test
//    public void calculateCostOfThreeUniqueBooks() {
//        List<Book> books = asList(new Book("ABC"),
//                new Book("DEF"),
//                new Book("GHI"));
//        DiscountCalculator discountCalculator = new DiscountCalculator(books);
//        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost();
//        assertEquals(21.60, priceOfBooks, 0);
//    }
//
//    @Test
//    public void calculateCostOfFiveUniqueBooks() {
//        List<Book> books = asList(new Book("ABC"),
//                new Book("DEF"),
//                new Book("GHI"),
//                new Book("JKL"),
//                new Book("MNO"));
//        DiscountCalculator discountCalculator = new DiscountCalculator(books);
//        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost();
//        assertEquals(30.00, priceOfBooks, 0);
//    }
//
//    @Test
//    public void calculateCostOfTheSameTwoBooks() {
//        List<Book> books = asList(new Book("ABC"),
//                new Book("ABC"));
//        DiscountCalculator discountCalculator = new DiscountCalculator(books);
//        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost();
//        assertEquals(16.00, priceOfBooks, 0);
//    }
//
//    @Test
//    public void calculateCostOfTheSameThreeBooks() {
//        List<Book> books = asList(new Book("ABC"),
//                new Book("ABC"),
//                new Book("ABC"));
//        DiscountCalculator discountCalculator = new DiscountCalculator(books);
//        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost();
//        assertEquals(24.00, priceOfBooks, 0);
//    }
//
//    @Test
//    public void calculateCostOfTheSameFourBooks() {
//        List<Book> books = asList(new Book("ABC"),
//                new Book("ABC"),
//                new Book("ABC"),
//                new Book("ABC"));
//        DiscountCalculator discountCalculator = new DiscountCalculator(books);
//        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost();
//        assertEquals(32.00, priceOfBooks, 0);
//    }
//
//    @Test
//    public void calculateCostOfTheSameFiveBooks() {
//        List<Book> books = asList(new Book("ABC"),
//                new Book("ABC"),
//                new Book("ABC"),
//                new Book("ABC"),
//                new Book("ABC"));
//        DiscountCalculator discountCalculator = new DiscountCalculator(books);
//        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost();
//        assertEquals(40.00, priceOfBooks, 0);
//    }
//
//    @Test
//    public void calculateCostOfThreeBooksWithTwoTheSame() {
//        List<Book> books = asList(new Book("ABC"),
//                new Book("ABC"),
//                new Book("DEF"));
//        DiscountCalculator discountCalculator = new DiscountCalculator(books);
//        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost();
//        assertEquals(23.20, priceOfBooks, 0);
//    }
//
//    @Test
//    public void calculateCostOfFourBooksWithTwoDifferent() {
//        List<Book> books = asList(new Book("ABC"),
//                new Book("ABC"),
//                new Book("ABC"),
//                new Book("DEF"));
//        DiscountCalculator discountCalculator = new DiscountCalculator(books);
//        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost();
//        assertEquals(31.20, priceOfBooks, 0);
//    }
//
//    @Test
//    public void calculateCostOfFiveBooksWithThreeDifferentAndThreeCopiesOfOne() {
//        List<Book> books = asList(new Book("ABC"),
//                new Book("ABC"),
//                new Book("ABC"),
//                new Book("DEF"),
//                new Book("GHI"));
//        DiscountCalculator discountCalculator = new DiscountCalculator(books);
//        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost();
//        assertEquals(37.60, priceOfBooks, 0);
//    }
//
//    @Test
//    public void calculateCostOfFiveBooksWithThreeDifferentAndTwoCopiesOfTwo() {
//        List<Book> books = asList(new Book("ABC"),
//                new Book("ABC"),
//                new Book("DEF"),
//                new Book("DEF"),
//                new Book("GHI"));
//        DiscountCalculator discountCalculator = new DiscountCalculator(books);
//        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost();
//        assertEquals(37.60, priceOfBooks, 0);
//    }
 
    ///////////////////////////
    
    @Test
    public void calculateDiscountPriceForOneBook() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                putItemsInBasket("ABC"));
        assertEquals(8.0, discountCalculator.calculateOptimumDiscountAvailable(), 0);
    }
    
    @Test
    public void calculateDiscountPriceForTwoBooks() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                putItemsInBasket("ABC", "DEF"));
        assertEquals(15.20, discountCalculator.calculateOptimumDiscountAvailable(), 0);
    }
    
    @Test
    public void calculateDiscountPriceForThreeBooks() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                putItemsInBasket("ABC", "DEF", "GHI"));
        assertEquals(21.60, discountCalculator.calculateOptimumDiscountAvailable(), 0);
    }
    
    @Test
    public void calculateDiscountPriceForFourBooks() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                putItemsInBasket("ABC", "DEF", "GHI", "JKL"));
        assertEquals(25.60, discountCalculator.calculateOptimumDiscountAvailable(), 0);
    }
    
    @Test
    public void calculateDiscountPriceForFiveBooks() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                putItemsInBasket("ABC", "DEF", "GHI", "JKL", "MNO"));
        assertEquals(30.00, discountCalculator.calculateOptimumDiscountAvailable(), 0);
    }
    
    @Test
    public void calculateDiscountPriceForTwoBooksTheSame() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                putItemsInBasket("ABC", "ABC"));
        assertEquals(16.00, discountCalculator.calculateOptimumDiscountAvailable(), 0);
    }
    
    @Test
    public void calculateDiscountPriceForThreeBooksWithTwoBooksTheSame() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                putItemsInBasket("ABC", "ABC", "DEF"));
        assertEquals(23.20, discountCalculator.calculateOptimumDiscountAvailable(), 0);
    }
    
    @Test
    public void calculateDiscountPriceForFourBooksWithTwoPairsOfBooksTheSame() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                putItemsInBasket("ABC", "ABC", "DEF", "DEF"));
        assertEquals(30.40, discountCalculator.calculateOptimumDiscountAvailable(), 0);
    }
    
    @Ignore
    public void calculateCostOfTheBooksCombo() {
        List<Book> books = asList(new Book("ABC"),
                new Book("ABC"),
                new Book("DEF"),
                new Book("DEF"),
                new Book("GHI"),
                new Book("GHI"),
                new Book("JKL"),
                new Book("MNO"));
        DiscountCalculator discountCalculator = new DiscountCalculator(books);
        final double priceOfBooks = discountCalculator.calculateDiscountedTotalCost( );
        assertEquals(51.60, priceOfBooks, 0);
    }
    
    private List<Book> putItemsInBasket(final String... itemNames) {
        List<Book> basket = new ArrayList<>();
        for (String itemName : itemNames) {
            Book book = new Book(itemName);
            basket.add(book);
        }
        return basket;
    }
}
