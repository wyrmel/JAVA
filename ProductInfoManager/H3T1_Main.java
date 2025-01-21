package Harjoitukset.Harjoitus_3;

import java.util.Scanner;

public class H3T1_Main {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        Product prod = new Product();

        // Anna tuotteen nimi
        System.out.println("\nAnna tuotteen nimi > ");
        String nimi = lukija.nextLine();
        prod.setName(nimi);

        // Anna tuotteen hinta
        System.out.println("\nAnna tuotteen hinta > ");
        double hinta = lukija.nextDouble();
        lukija.nextLine(); // Poistaa rivinvaihdon
        prod.setPrice(hinta);

        // Anna tuotteen kuvaus
        System.out.println("\nAnna tuotteen kuvaus > ");
        String kuvaus = lukija.nextLine();
        prod.setDescription(kuvaus);

        ProductInfo.printProducts(new Product[] {prod});

        lukija.close();
    }
    
}
