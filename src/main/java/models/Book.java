package models;

public class Book {
    private static final double PRICE = 8.00;
    private String title;
    
    public Book(final String title) {
        this.title = title;
    }
    
    public double getPrice() {
        return PRICE;
    }
    
    public String getTitle() {
        return title;
    }
}
