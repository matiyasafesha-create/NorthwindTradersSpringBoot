package com.pluralsight.NorthwindTradersSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;



@Component
public class NorthwindTradersCLI implements CommandLineRunner {

    private final Scanner scanner;
    private final ProductDao productDao;

    @Autowired

    public NorthwindTradersCLI (ProductDao productDao){
        this.productDao = productDao;
        this.scanner = new Scanner(System.in);


    }

    @Override

    public void run(String... args) throws  Exception{


            boolean running = true;

            while(running){
                System.out.println("Welcome to the test Product Environment");
                System.out.println(
                        "1 - View All Listing\n" +
                                "2 - Search by name\n" +
                                "3 - Add products\n" +
                                "4 - Remove Products\n" +
                                "5 - Update Product List\n");
                System.out.print("Enter Your Option Here: ");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        viewAllListing ();
                        break;

                    case 2:
                        processSearchName();
                        break;

                    case 3:
                        addListing();
                        break;

                }
            }
        }


        public void  viewAllListing (){
            System.out.println("List of all Available Products");
            productDao.getAll().forEach(System.out ::println);
        }

        public void addListing(){
            System.out.println("Add Products Here");

            System.out.print("Enter Your ProductID:");
            int product = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter The name of the Product:");
            String name = scanner.nextLine();

            System.out.print("Enter The Category:");
            String category = scanner.nextLine();

            System.out.print("Enter The price of the Product");
            double price = scanner.nextFloat();

            productDao.add(new Product(product,name,category,price));
            System.out.println("Successfully Added !!");


        }

        public void processSearchName() {
        scanner.nextLine();
            System.out.print("Enter the Product name to Search:");
            String search = scanner.nextLine().toLowerCase();

            List<Product> results = productDao.getByName(search);

            if(results.isEmpty()){
                System.out.println("No Products Found. Try Another Name");
            }else {
                System.out.println("Here are the Products ");
                results.forEach(System.out ::println);
            }
        }

        public void processRemoveProduct(){
        scanner.nextLine();
            System.out.print("What Product Do you Want to Remove:");

        }

    }

