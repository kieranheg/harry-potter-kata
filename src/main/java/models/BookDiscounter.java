package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDiscounter {
    private static final double BOOK_PRICE = 8.00;
    final static Map<Integer, Integer> DISCOUNTS = new HashMap<>();
    static {
        DISCOUNTS.put(1, 0);
        DISCOUNTS.put(2, 5);
        DISCOUNTS.put(3, 10);
        DISCOUNTS.put(4, 20);
        DISCOUNTS.put(5, 25);
    }
    
    HashMap<String, Integer> booksCounter = new HashMap<>();
    
    public double calculateTotalCost(final List<Book> booksToCount) {
        setBooksToDiscount(booksToCount);
        calculateIndividualBookCounts(booksToCount);
    
        return calculateCostOfBooks();
    }
    
    private double calculateCostOfBooks() {
        double totalCostOfBooks = 0.00;
        for (Map.Entry<String, Integer> bookCountItem : booksCounter.entrySet()) {
            int numberOfBooksToPurchase = bookCountItem.getValue();
            int discount = getDiscountForBooksCount(numberOfBooksToPurchase);
            totalCostOfBooks += calculateBookCostWithDiscount(numberOfBooksToPurchase, discount);
        };
        return totalCostOfBooks;
    }
    
    private double calculateBookCostWithDiscount(final Integer numberOfBooksToPurchase, final int discount) {
        double costOfBooks = numberOfBooksToPurchase * BOOK_PRICE;
        double discountOnBooks = discount > 0 ? costOfBooks / (100 / discount) : 0.00;
        return costOfBooks - discountOnBooks;
    }
    
    public int getDiscountForBooksCount(final int numberOfBooks) {
        return DISCOUNTS.get(numberOfBooks);
    }
    
    
    public void setBooksToDiscount(final List<Book> booksToCount) {
        booksCounter = new HashMap<>();
        for (Book book : booksToCount) {
            booksCounter.put(book.getTitle(), new Integer(0));
        }
    }
    
    public HashMap<String, Integer> getBookCounters() {
        return booksCounter;
    }
    
    public void calculateIndividualBookCounts(final List<Book> booksToCount) {
        for (Book book : booksToCount) {
            Integer currentNumBooks = booksCounter.get(book.getTitle()) + 1;
            booksCounter.put(book.getTitle(), currentNumBooks);
        }
    }
}
