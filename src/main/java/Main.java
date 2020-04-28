import api.ProductService;
import api.UserRegisterLoginFacade;
import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.User;
import entity.enums.Color;
import entity.enums.Material;
import entity.enums.SkinType;
import entity.parser.ColorParser;
import entity.parser.MaterialParser;
import entity.parser.SkinParser;
import facade.UserRegisterLoginFacadeImpl;
import service.ProductServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void startMenu() {
        System.out.println("MANAGEMENT MENU");
        System.out.println("1 - Zaloguj się");
        System.out.println("2 - Zarejestruj się");
        System.out.println("0 - Wyjdź");
    }

    public static void loggedMenu() {
        System.out.println("MANAGEMENT MENU");
        System.out.println("1 - Dodaj nowy product");
        System.out.println("2 - Wyświetl swoje produkty");
        System.out.println("0 - Wyloguj się");
    }

    public static void productTypeMenu() {
        System.out.println("1 - Dodaj buty");
        System.out.println("2 - Dodaj ubrania");
        System.out.println("3 - Inne");
    }

    public static Product createOtherProduct() {
        String productName;
        double price, weight;
        int productCount;
        Color color;

        System.out.println("Product name: ");
        productName = scanner.next();

        System.out.println("Product price: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Product price: ");
            scanner.next();
        }
        price = scanner.nextDouble();

        System.out.println("Product weight: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Product weigh: ");
            scanner.next();
        }
        weight = scanner.nextDouble();

        System.out.println("Product color: Chose: RED, BLUE, GREEN, BLACK, WHITE, YELLOW");
        color = ColorParser.colorParser(scanner.next());

        System.out.println("Product amount: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Product amount: ");
            scanner.next();
        }
        productCount = scanner.nextInt();

        return new Product(1L, productName, price, weight, productCount, color);
    }


    public static Cloth createCloth() {
        String productName, size;
        double price, weight;
        int productCount;
        Color color;
        Material material;


        System.out.println("Product name: ");
        productName = scanner.next();

        System.out.println("Product price: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Product price: ");
            scanner.next();
        }
        price = scanner.nextDouble();

        System.out.println("Product weight: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Product weigh: ");
            scanner.next();
        }
        weight = scanner.nextDouble();

        System.out.println("Product color: Chose: RED, BLUE, GREEN, BLACK, WHITE, YELLOW");
        color = ColorParser.colorParser(scanner.next());

        System.out.println("Product amount: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Product amount: ");
            scanner.next();
        }
        productCount = scanner.nextInt();

        System.out.println("Product Size: ");
        size = scanner.next();

        System.out.println("Product material: Chose: LEATHER, FUR, COTTON, WOOL, POLYESTERS");
        material = MaterialParser.materialParser(scanner.next());

        return new Cloth(1L, productName, price, weight, productCount, color, size, material);
    }

    public static Boots createBoots() {
        String productName;
        double price, weight;
        int productCount, size;
        SkinType skinType;
        Color color;


        System.out.println("Product name: ");
        productName = scanner.next();

        System.out.println("Product price: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Product price: ");
            scanner.next();
        }
        price = scanner.nextDouble();

        System.out.println("Product weight: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Product weigh: ");
            scanner.next();
        }
        weight = scanner.nextDouble();

        System.out.println("Product color: Chose: RED, BLUE, GREEN, BLACK, WHITE, YELLOW");
        color = ColorParser.colorParser(scanner.next());
        System.out.println("Product amount: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Product amount: ");
            scanner.next();
        }
        productCount = scanner.nextInt();

        System.out.println("Product size: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Product size: ");
            scanner.next();
        }
        size = scanner.nextInt();

        System.out.println("Product material? Chose:  NATURAL, ARTIFICIAL" );
        skinType= SkinParser.skinTypeParser(scanner.next());

        return new Boots(1L, productName, price, weight, productCount, color, size, skinType);
    }

    public static void main(String[] args) {
        UserRegisterLoginFacade userRegisterLoginFacade = UserRegisterLoginFacadeImpl.getInstance();
        ProductService productService = ProductServiceImpl.getInstance();

        boolean appOn = true;
        boolean loggedOn = false;
        int read;

        while (appOn) {
            startMenu();
            while (!scanner.hasNextInt()) {
                System.out.println("Podaj liczbę: ");
            }
            read = scanner.nextInt();
            switch (read) {
                case 1:
                    System.out.println("Podaj login: ");
                    String login = scanner.next();
                    System.out.println("Podaj hasło: ");
                    String password = scanner.next();
                    if (userRegisterLoginFacade.loginUser(login, password)) {
                        loggedOn = true;
                        System.out.println("Zalogowałeś się.");
                    } else {
                        System.out.println("Niepoprawne dane.");
                    }
                    break;

                case 2:
                    System.out.println("Podaj login: ");
                    String loginReg = scanner.next();
                    System.out.println("Podaj hasło : ");
                    String passwordReg = scanner.next();
                    User user = new User(1L, loginReg, passwordReg);
                    if (userRegisterLoginFacade.registerUser(user)) {
                        System.out.println("Jesteś zarejestrowany!");
                    } else {
                        System.out.println("Coś poszło nie tak, spróbuj ponownie");
                    }
                    break;
                case 0:
                    appOn = false;
                    break;
            }
            while (loggedOn) {
                loggedMenu();
                while (!scanner.hasNextInt()) {
                    System.out.println("Podaj liczbę: ");
                }
                read = scanner.nextInt();
                switch (read) {
                    case 1:
                        productTypeMenu();
                        while (!scanner.hasNextInt()) {
                            System.out.println("Podaj liczbę: ");
                        }
                        Product product = null;
                        read = scanner.nextInt();
                        switch (read) {
                            case 1:
                                product = createBoots();
                                break;
                            case 2:
                                product = createCloth();
                                break;
                            case 3:
                                product = createOtherProduct();
                                break;
                        }
                        if (productService.saveProduct(product)) {
                            System.out.println("Produkt został utworzony.");
                        } else {
                            System.out.println("Produkt nie został utworzony.");
                        }
                        break;
                    case 2:
                        List <Product> list=null;
                        try {
                            list=productService.getAllProducts();
                        }catch (IOException e){
                            System.out.println(e.getMessage());
                        }
                        for (Product prod:list
                             ) {
                            System.out.println(prod.toString());
                        }
                        break;
                    case 0:
                        loggedOn = false;
                        break;

                }
            }

        }
    }
}