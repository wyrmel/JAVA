package ProductInfoManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        Product prod = new Product();

        // Give product name
        System.out.println("\nAnna tuotteen nimi > ");
        String nimi = lukija.nextLine();
        prod.setName(nimi);

        // Give product price
        System.out.println("\nAnna tuotteen hinta > ");
        double hinta = lukija.nextDouble();
        lukija.nextLine(); // Removes line break
        prod.setPrice(hinta);

        // Give product description
        System.out.println("\nAnna tuotteen kuvaus > ");
        String kuvaus = lukija.nextLine();
        prod.setDescription(kuvaus);

        ProductInfo.printProducts(new Product[] {prod});

        lukija.close();
    }
    
}
