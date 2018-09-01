package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountCalculator {
    private static final double ITEM_PRICE = 8.00;
    final static Map<Integer, Integer> DISCOUNTS = new HashMap<>();
    
    static {
        DISCOUNTS.put(1, 0);
        DISCOUNTS.put(2, 5);
        DISCOUNTS.put(3, 10);
        DISCOUNTS.put(4, 20);
        DISCOUNTS.put(5, 25);
    }
    
    private HashMap<String, Integer> booksCounter = new HashMap<>();
    private int numItemsInBasket = 0;
    
    public int getNumItemsInBasket() {
        return numItemsInBasket;
    }
    
    public void setNumItemsInBasket(final int numItemsInBasket) {
        this.numItemsInBasket = numItemsInBasket;
    }
    
    public double calculateDiscountedTotalCost(final List<Book> booksToCount) {
        setNumItemsInBasket(booksToCount.size());
        setItemsToDiscount(booksToCount);
        calculateIndividualItemCounts(booksToCount);
        
        double discountedItemsCost = getDiscountedItemCost();
        double nonDiscountedItemsCost = getNonDiscountedItemCost();
        return discountedItemsCost + nonDiscountedItemsCost;
    }
    
    private double getNonDiscountedItemCost() {
        return ITEM_PRICE * (getNumItemsInBasket() - booksCounter.size());
    }
    
    private double getDiscountedItemCost() {
        double totalCostBeforeDiscount = ITEM_PRICE * booksCounter.size();
        int discountApplicable = getDiscountForNumUniqueItems(booksCounter.size());
        double discountedCost = discountApplicable > 0
                ? totalCostBeforeDiscount / (100 / discountApplicable)
                : 0.00;
        return totalCostBeforeDiscount - discountedCost;
    }
    
    private double getTotalCostOfItems() {
        double totalCost = 0.00;
        for (Map.Entry<String, Integer> bookCountItem : booksCounter.entrySet()) {
            int numberOfAItemToPurchase = bookCountItem.getValue();
            totalCost += ITEM_PRICE * numberOfAItemToPurchase;
        }
        return totalCost;
    }
    
    private double calculateItemCostWithDiscount(final double totalCost) {
        int discountApplicable = getDiscountForNumUniqueItems(booksCounter.size());
        double discountOnItems = discountApplicable > 0
                ? totalCost / (100 / discountApplicable)
                : 0.00;
        return totalCost - discountOnItems;
    }
    
    public int getDiscountForNumUniqueItems(final int numberOfItems) {
        return DISCOUNTS.get(numberOfItems);
    }
    
    
    public void setItemsToDiscount(final List<Book> booksToCount) {
        booksCounter = new HashMap<>();
        for (Book book : booksToCount) {
            booksCounter.put(book.getTitle(), new Integer(0));
        }
    }
    
    public HashMap<String, Integer> getItemCounters() {
        return booksCounter;
    }
    
    public void calculateIndividualItemCounts(final List<Book> booksToCount) {
        for (Book book : booksToCount) {
            Integer currentNumItems = booksCounter.get(book.getTitle()) + 1;
            booksCounter.put(book.getTitle(), currentNumItems);
        }
    }
}
