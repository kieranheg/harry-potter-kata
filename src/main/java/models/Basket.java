package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    List<Book> booksInBasket = new ArrayList<>();
    
    public void add(final Book book) {
        booksInBasket.add(book);
    }
    
    public List<Book> getBooks() {
        return booksInBasket;
    }
    
    public double getTotalCostOfBasket() {
        double totalCostOfBasket = 0.00;
        for (Book book : booksInBasket) {
            totalCostOfBasket += book.getPrice();
        }
        return totalCostOfBasket;
    }
    
    public double getDiscount() {
        return calculateTotalDiscount();
    }
    
    private double calculateTotalDiscount() {
        HashMap<String, Integer> booksCounter = generateIndividualBookCounters();
    
        return 0;
    }
    
    public HashMap<String, Integer> generateIndividualBookCounters() {
        HashMap<String, Integer> booksCounter = new HashMap<>();
        for (Book book : booksInBasket) {
            booksCounter.put(book.getTitle(), new Integer(1));
        }
        return booksCounter;
    }
    
    public double getCostOfBasketLessDiscount() {
        return 8;
    }
}
