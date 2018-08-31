package models;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Basket_UT {
    @Test
    public void costAndDiscountOneBook() {
        Basket basket = new Basket();
        basket.add(new Book("Book1"));
        assertFalse(basket.getBooks().isEmpty());
        assertEquals(8.00, basket.getTotalCostOfBasket(),0);
        assertEquals(0.00, basket.getDiscount(),0);
        assertEquals(8.00, basket.getCostOfBasketLessDiscount(),0);
    }
    
    @Test
    public void buildBookerCounters() {
        Basket basket = new Basket();
        basket.add(new Book("Book1"));
        basket.add(new Book("Book2"));
        basket.add(new Book("Book1"));
        basket.add(new Book("Book3"));
    
        HashMap<String, Integer> bookCounters = basket.generateIndividualBookCounters();
        
        assertTrue(bookCounters.containsKey("Book1"));
        assertTrue(bookCounters.containsKey("Book2"));
        assertTrue(bookCounters.containsKey("Book3"));
        assertEquals(new Integer(1), bookCounters.get("Book1"));
        assertEquals(new Integer(1), bookCounters.get("Book2"));
        assertEquals(new Integer(1), bookCounters.get("Book3"));
    }
    
    @Test
    public void costAndDiscountWithTwoBooks() {
        Basket basket = new Basket();
        basket.add(new Book("Book1"));
        basket.add(new Book("Book2"));
        assertEquals(2, basket.getBooks().size());
        assertEquals(16.00, basket.getTotalCostOfBasket(),0);
        assertEquals(0.80, basket.getDiscount(),0);
        assertEquals(8.00, basket.getCostOfBasketLessDiscount(),0);
    }
}
