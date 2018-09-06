package models;

import java.util.*;

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
    
    private List<Book> basket;
    private Map<String, Integer> basketItemCounts = new HashMap<>();
    
    public DiscountCalculator(final List<Book> basket) {
        this.basket = basket;
        initDiscountBasketCounters();
    }
    
    private void initDiscountBasketCounters() {
        for (Book book : basket) {
            if (basketItemCounts.containsKey(book.getTitle())) {
                Integer currentNumItems = basketItemCounts.get(book.getTitle()) + 1;
                basketItemCounts.put(book.getTitle(), currentNumItems);
            } else {
                basketItemCounts.put(book.getTitle(), new Integer(1));
            }
        }
    }
    
    public double calculateOptimumDiscountAvailable() {
        double discountedCost = 0.0;
        while (!basketItemCounts.isEmpty()) {
            List<String> rowFromBasket = getNextRowFromBasket();
            int rowDiscount = getDiscountForNumUniqueItems(rowFromBasket.size());
            discountedCost += getDiscountedItemCost(rowFromBasket.size());
            removePurchasedItemsFromBasketCounts();
        }
        return discountedCost;
        
        
        // calculate discount for unique items in basket
        // pop 1st row of unique item
        // repeat calculation
    }
    
    private int calculateRowDiscount() {
        int numUniqueItemsInBasket = basketItemCounts.values().size();
        return getDiscountForNumUniqueItems(numUniqueItemsInBasket);
    }
    
    private List<String> getNextRowFromBasket() {
        List<String> nextRowOfItems = new ArrayList<>();
        for (String title : basketItemCounts.keySet()) {
            nextRowOfItems.add(title);
            Integer itemCount = basketItemCounts.get(title) - 1;
            basketItemCounts.put(title, itemCount);
        }
        return nextRowOfItems;
    }
    
    private void removePurchasedItemsFromBasketCounts() {
        Iterator it = basketItemCounts.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if ((Integer) pair.getValue() == 0) {
                it.remove();
            }
        }
    }
    
    public double calculateDiscountedTotalCost() {
        double discountedItemsCost = getDiscountedItemCost();
        double nonDiscountedItemsCost = getNonDiscountedItemCost();
        return discountedItemsCost + nonDiscountedItemsCost;
    }
    
    private double getNonDiscountedItemCost() {
        return ITEM_PRICE * (basket.size() - basketItemCounts.size());
    }
    
    private double getDiscountedItemCost(final int rowFromBasketSize) {
        double totalCostBeforeDiscount = ITEM_PRICE * rowFromBasketSize;
        int discountApplicable = getDiscountForNumUniqueItems(rowFromBasketSize);
        double discountedCost = discountApplicable > 0
                ? totalCostBeforeDiscount / (100 / discountApplicable)
                : 0.00;
        return totalCostBeforeDiscount - discountedCost;
    }
    
    private double getDiscountedItemCost() {
        double totalCostBeforeDiscount = ITEM_PRICE * basketItemCounts.size();
        int discountApplicable = getDiscountForNumUniqueItems(basketItemCounts.size());
        double discountedCost = discountApplicable > 0
                ? totalCostBeforeDiscount / (100 / discountApplicable)
                : 0.00;
        return totalCostBeforeDiscount - discountedCost;
    }
    
    private double getTotalCostOfItems() {
        double totalCost = 0.00;
        for (Map.Entry<String, Integer> bookCountItem : basketItemCounts.entrySet()) {
            int numberOfAItemToPurchase = bookCountItem.getValue();
            totalCost += ITEM_PRICE * numberOfAItemToPurchase;
        }
        return totalCost;
    }
    
    private double calculateItemCostWithDiscount(final double totalCost) {
        int discountApplicable = getDiscountForNumUniqueItems(basketItemCounts.size());
        double discountOnItems = discountApplicable > 0
                ? totalCost / (100 / discountApplicable)
                : 0.00;
        return totalCost - discountOnItems;
    }
    
    public int getDiscountForNumUniqueItems(final int numberOfItems) {
        return DISCOUNTS.get(numberOfItems);
    }
    
    
    public Map<String, Integer> getAllItemCounts() {
        return basketItemCounts;
    }
    
}
