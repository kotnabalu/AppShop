import api.ProductService;
import api.UserRegisterLoginFacade;
import api.UserService;
import entity.Product;
import entity.User;
import entity.enums.Color;
import entity.parser.ColorParser;
import facade.UserRegisterLoginFacadeImpl;
import service.ProductServiceImpl;
import service.UserServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static User loggedUser;
    static UserService userService = UserServiceImpl.getInstance();

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
        System.out.println("3 - Usuń produkt");
        System.out.println("0 - Wyloguj się");
    }

//    public static void productTypeMenu() {
//        System.out.println("1 - Dodaj buty");
//        System.out.println("2 - Dodaj ubrania");
//        System.out.println("3 - Inne");
//    }

    public static Product createProduct() {
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
        long user_id = loggedUser.getId();

        return new Product(1L, productName, price, weight, productCount, color, user_id);
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
                        loggedUser = userService.getUserByLogin(login);
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
                    if (userRegisterLoginFacade.registerUser(user).equals("Registered user")) {
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

//                        while (!scanner.hasNextInt()) {
//                            System.out.println("Podaj liczbę: ");
//                        }
//                        read = scanner.nextInt();
                       Product product = createProduct();

                        if (productService.saveProduct(product)) {
                            System.out.println("Produkt został utworzony.");
                        } else {
                            System.out.println("Produkt nie został utworzony.");
                        }
                        break;
                    case 2:

                        List<Product> list = productService.getProductsByUserId(loggedUser.getId());
                        if (!list.isEmpty()) {
                            for (Product prod : list
                            ) {
                                System.out.println(prod.toString());
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Podaj nazwę produktu");
                        String name = scanner.next();
                        if (productService.removeProductByNameAndUserId(name, loggedUser.getId())) {
                            System.out.println("Usuwanie powiodło się");
                        } else System.out.println("Usuwanie nie powiodło się");

                        break;
                    case 0:
                        loggedOn = false;
                        break;

                }
            }

        }
    }
}
