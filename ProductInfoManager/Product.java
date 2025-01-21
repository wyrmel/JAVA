package Harjoitukset.Harjoitus_3;

public class Product {
    // Tuotteen nimi
    private String name;
    // Tuotteen hinta
    private double price;
    // Tuotteen kuvaus
    private String description;

    // Asettaa tuotteelle uuden nimen
    public void setName(String name) {
        this.name = name;
    }

    // Asettaa tuotteelle uuden hinnan
    public void setPrice(double price) {
        this.price = price;
    }

    // Asettaa tuotteelle uuden kuvauksen
    public void setDescription(String description) {
        this.description = description;
    }

    // Palauttaa tuotteen nimen
    public String getName() {
        return name;
    }

    // Palauttaa tuotteen hinnan
    public double getPrice() {
        return price;
    }

    // Palauttaa tuotteen kuvauksen
    public String getDescription() {
        return description;
    }
    
}
