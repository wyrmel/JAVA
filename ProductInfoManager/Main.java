package ProductInfoManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Give product count
        System.out.println("How many products would you like to add? > ");
        int productCount = scanner.nextInt();
        scanner.nextLine(); // Remove line break

        // Create an array to store the products
        Product[] products = new Product[productCount];

        // Loop to input product details
        for (int i = 0; i < productCount; i++) {
            System.out.println("\nEnter product details (" + (i + 1) + "/" + productCount + "):");

            Product prod = new Product();

            // Give product name
            System.out.println("Enter product name > ");
            String name = scanner.nextLine();
            prod.setName(name);

            // Give product price
            System.out.println("Enter product price > ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Removes line break
            prod.setPrice(price);

            // Give product description
            System.out.println("Enter product description > ");
            String description = scanner.nextLine();
            prod.setDescription(description);

            // Save the product to the array
            products[i] = prod;
        }

        // Print all products
        ProductInfo.printProducts(products);

        // Close the scanner
        scanner.close();
    }
}

