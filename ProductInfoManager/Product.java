package ProductInfoManager;

public class Product {
    // Product name
    private String name;
    // Product price
    private double price;
    // Product description
    private String description;

    // Gives product a new name
    public void setName(String name) {
        this.name = name;
    }

    // Gives product a new price
    public void setPrice(double price) {
        this.price = price;
    }

    // Gives product a new description
    public void setDescription(String description) {
        this.description = description;
    }

    // Returns product name
    public String getName() {
        return name;
    }

    // Returns product price
    public double getPrice() {
        return price;
    }

    // Returns product description
    public String getDescription() {
        return description;
    }
    
}
