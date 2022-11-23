package org.example;

import java.util.Scanner;

public class Main {

    private final static int MAX_POINTS_IN_MENU = 8;

    public static void main(String[] args) {
        TaxBase taxBase = TaxBase.taxBaseInstance();
        taxBase.setPersonSet(new Person(3456789126L, "Roman", "Espanola",
                19, 10, 1981));
        taxBase.setPersonSet(new Person(2465123981L, "Stepan", "Kachynskiy",
                2, 3, 1996));
        taxBase.setPersonSet(new Person(7565436612L, "Panas", "Maselko",
                7, 1, 1988));
        taxBase.setPersonSet(new Person(5423911278L, "Olena", "Svygach",
                31, 12, 1998));
        taxBase.addNewFine(3456789126L, new Fine("BA-PA12345", 540.25f,
                "Automobile fine", "Ivano-Frankivsk", "No airbag on the driver."));
        taxBase.addNewFine(3456789126L, new Fine("FK-SX56401", 720.00f,
                "Automobile fine", "Lviv", "No seat for kid in the car interior."));
        taxBase.addNewFine(3456789126L, new Fine("TR-567234LPO", 1028.18f,
                "Land tax", "Ivano-Frankivsk", "During 2022 year the land tax was not paid."));
        taxBase.addNewFine(2465123981L, new Fine("LF-BV55741", 750.00f,
                "Automobile fine", "Lviv", "The speed of car in the city was 72 km/h."));
        taxBase.addNewFine(7565436612L, new Fine("WD-CL28611", 1250.00f,
                "Automobile fine", "Odesa", "The speed of car in the city was 113 km/h."));
        taxBase.addNewFine(7565436612L, new Fine("QG-3997710MNB", 4350.35f,
                "Land tax", "Odesa", "During period from 2017 year till 2022 year the land tax was not paid."));
        menu(taxBase);
    }

    public static void menu(TaxBase taxBase) {
        while (true) {
            System.out.println("-------------------------------------");
            System.out.println("Points of menu: ");
            System.out.println("1 - Show all tax base.");
            System.out.println("2 - Show taxes by identity number.");
            System.out.println("3 - Show taxes by type of the fine.");
            System.out.println("4 - Show taxes by city.");
            System.out.println("5 - Add new person.");
            System.out.println("6 - Add new fine to the person.");
            System.out.println("7 - Remove the fine.");
            System.out.println("8 - Change the information about person and fine.");
            System.out.println("0 - Exit from the base.");
            System.out.print("Choose the point of menu: ");
            int choice = menuPoint();
            switch (choice) {
                case 1:
                    System.out.println();
                    taxBase.baseShow();
                    break;
                case 2:
                    System.out.println();
                    taxBase.showByIdentityNumber(identityNumber());
                    break;
                case 3:
                    System.out.print("\nEnter the type of the fine: ");
                    taxBase.showFinesByType(userInput());
                    break;
                case 4:
                    System.out.print("\nEnter the city name: ");
                    taxBase.showFinesByCity(userInput());
                    break;
                case 5:
                    System.out.println();
                    taxBase.addNewPerson(identityNumber());
                    break;
                case 6:
                    System.out.println();
                    taxBase.addNewFine(identityNumber());
                    break;
                case 7:
                    System.out.println();
                    taxBase.removeFine(identityNumber());
                    break;
                case 8:
                    System.out.println();
                    taxBase.changeInfo(identityNumber());
                    break;
                case 0:
                default:
                    return;
            }
        }
    }

    private static int menuPoint() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            choice = scanner.nextInt();
            if (choice < 0 || choice > MAX_POINTS_IN_MENU) {
                System.out.print("Enter the digit from 0 till " + MAX_POINTS_IN_MENU + ": ");
            }
        } while (choice < 0 || choice > MAX_POINTS_IN_MENU);
        return choice;
    }

    public static long identityNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Identity number must contains the 10 digits.");
        System.out.print("Enter the Person's identity number: ");
        long number;
        do {
            number = scanner.nextLong();
            if (number < 1000000000L || number > 9999999999L) {
                System.out.print("Enter the 10 digits identity number: ");
            }
        } while (number < 1000000000L || number > 9999999999L);
        return number;
    }

    private static String userInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}