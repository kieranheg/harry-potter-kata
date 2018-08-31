package models;

public class Book {
    private String title;
    private double price;
    
    public Book(final String title, final double price) {
        this.title = title;
        this.price = price;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setPrice(final double price) {
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getTitle() {
        return title;
    }
}
