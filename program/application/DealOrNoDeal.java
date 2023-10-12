package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DealOrNoDeal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Liste der Geldbeträge in den Koffern
        ArrayList<Double> amounts = new ArrayList<>();
        Collections.addAll(amounts, 0.01, 1.0, 5.0, 10.0, 25.0, 50.0, 75.0, 100.0, 200.0, 300.0, 400.0, 500.0, 750.0, 1000.0, 5000.0, 10000.0, 25000.0, 50000.0, 75000.0, 100000.0, 200000.0, 300000.0, 400000.0, 500000.0, 750000.0, 1000000.0);

        // Zufällige Verteilung der Geldbeträge in den Koffern
        Collections.shuffle(amounts);

        System.out.println("Willkommen bei Deal or No Deal!");

        int numCases = 26; // Anzahl der Koffer auf 26 festlegen

        // Die Spieler-Auswahl speichern
        int playerCase;
        while (true) {
            System.out.print("Wählen Sie einen Koffer (1 - " + numCases + "): ");
            playerCase = scanner.nextInt();
            if (playerCase >= 1 && playerCase <= numCases) {
                break;
            } else {
                System.out.println("Bitte geben Sie eine gültige Koffer-Nummer ein.");
            }
        }

        System.out.println("Sie haben Koffer " + playerCase + " ausgewählt.");

        // Das Hauptspiel mit neun Runden
        for (int round = 1; round <= 9; round++) {
            int kofferToOpen = 7 - round; // Berechne die Anzahl der zu öffnenden Koffer

            System.out.println("\nRunde " + round + ": Öffnen Sie " + kofferToOpen + " Koffer.");

            for (int i = 1; i <= numCases; i++) {
                if (i == playerCase) {
                    System.out.print("  [" + i + "]");
                } else {
                    System.out.print("   " + i);
                }
                if (i % 5 == 0) {
                    System.out.println();
                }
            }

            for (int j = 0; j < kofferToOpen; j++) {
                int choice;
                while (true) {
                    System.out.print("\nWählen Sie einen Koffer, den Sie öffnen möchten (1 - " + numCases + "): ");
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= numCases && choice != playerCase) {
                        break;
                    } else {
                        System.out.println("Bitte geben Sie eine gültige Koffer-Nummer ein, die nicht Ihr eigener Koffer ist.");
                    }
                }

                double openedCase = amounts.remove(choice - 1);
                System.out.println("Sie haben Koffer " + choice + " geöffnet und darin befand sich $" + openedCase + "!");
                numCases--;
            }

            // Bankangebot
            double bankOffer = calculateBankOffer(amounts, numCases);
            System.out.println("\nAngebot des Bankiers: $" + String.format("%.2f", bankOffer));
            System.out.print("Deal (D) oder No Deal (N)?: ");
            String decision = scanner.next().toLowerCase();

            if (decision.equals("d")) {
                System.out.println("Herzlichen Glückwunsch! Sie haben $" + String.format("%.2f", bankOffer) + " gewonnen!");
                break; // Das Spiel endet, wenn der Spieler das Angebot annimmt
            }
        }

        double playerAmount = amounts.get(0);
        System.out.println("Glückwunsch! Ihr Koffer (Nummer " + playerCase + ") enthält $" + String.format("%.2f", playerAmount) + ".");
    }

    // Funktion zur Berechnung des Bankangebots
    public static double calculateBankOffer(ArrayList<Double> amounts, int numCases) {
        return amounts.stream().mapToDouble(Double::doubleValue).sum() / numCases;
    }
}
