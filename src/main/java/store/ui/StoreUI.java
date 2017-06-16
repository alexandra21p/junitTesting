package store.ui;

import store.controller.StoreController;
import store.model.Product;
import store.model.validator.ValidatorException;

import java.util.ArrayList;
import java.util.Scanner;


public class StoreUI {
    public StoreController ctrl;
    Scanner in;
    public StoreUI(StoreController ctrl){
        this.ctrl = ctrl;
        this.in = new Scanner(System.in);
    }
    public void printMenu() {
        String menu;
        menu = "These are the available commands:\n";
        menu += "\t 1 - Add a new Product \n";
        menu += "\t 2 - Displays all products from category\n";
        menu += "\t 3 - Stock situation for all products \n";
        menu += "\t 4 - Stock situation for a certain product \n";
        menu += "\t 0 - to exit; \n";
        System.out.println(menu);
    }

    public void AddNewProduct()
    {
        int pCode, pQuantity;
        System.out.println("Give the Product code:");
        try {
            pCode = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid code!");
            pCode = -1;
        }

        System.out.println("Give the product name:");
        String pName = in.nextLine();

        System.out.println("Give the product category:");
        String pCategory = in.nextLine();

        System.out.println("Give the quantity:");
        try {
            pQuantity = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity!");
            pQuantity = -1;
        }
        Product p = new Product(pCode,pName,pCategory,pQuantity);
        try {
            ctrl.addProduct(p);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    public void DisplayCategory()
    {
        System.out.println("Give category");
        String cat=in.nextLine();
        ArrayList<Product> temp = ctrl.getProductsCategory(cat);

        for (Product p : temp) {
            System.out.println(p.toString());
        }
    }

    public void DisplayStock()
    {

        ArrayList<Product> temp = ctrl.stockSituation();

        for (Product p : temp) {
            System.out.println(p.toString());
        }
    }

    public void DisplayStockFor()
    {
        System.out.println("Give product name");
        String cat=in.nextLine();
        ArrayList<Product> temp = ctrl.stockSituationProduct(cat);

        for (Product p : temp) {
            System.out.println(p.toString());
        }
    }

    public int readCommand() {
        System.out.println("Give a command: ");
        int c = 0;
        c = Integer.parseInt(in.nextLine());
        return c;
    }

    public void executeCommand(int c) {
        switch(c) {
            case 1:
                AddNewProduct();
                break;
            case 2:
                DisplayCategory();
                break;
            case 3:
                DisplayStock();
                break;
            case 4:
                DisplayStockFor();
                break;
            default:
                System.out.println("B'bye now!...\n");
                break;
        }
    }

    public void run() {
        int c;
        do {
            printMenu();
            c = readCommand();
            executeCommand(c);
        } while(c != 0);

    }



}
