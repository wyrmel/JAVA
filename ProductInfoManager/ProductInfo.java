package ProductInfoManager;

// Save products by initializing each Product object in the array.
public class ProductInfo {
    static void saveProducts(Product [] products) {
        for (int i = 0; i < products.length; i++) {
            products[i] = new Product();
        }
        printProducts(products);
    }

    // Print details of all products in the array.
    public static void printProducts(Product [] products) {
        for (int i = 0; i < products.length; i++) {
            System.out.println("\nName: " + products[i].getName());
            System.out.println("Price: " + products[i].getPrice());
            System.out.println("Description: " + products[i].getDescription());
        }
    }
    
}
