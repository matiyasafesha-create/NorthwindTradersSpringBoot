package com.pluralsight.NorthwindTradersSpringBoot;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component

public class SimpleProductDao implements ProductDao {
    private List<Product> products;


    public SimpleProductDao(){
        this.products = new ArrayList<>();
        this.products.add(new Product(123,"Laptop","electronics",899.00));
        products.add(new Product(102, "Wireless Mouse", "Electronics", 29.99));
        products.add(new Product(103, "Mechanical Keyboard", "Electronics", 119.99));
        products.add(new Product(104, "USB-C Charger", "Electronics", 24.99));
        products.add(new Product(105, "Bluetooth Speaker", "Electronics", 59.99));

        products.add(new Product(201, "Office Chair", "Furniture", 149.99));
        products.add(new Product(202, "Standing Desk", "Furniture", 399.00));
        products.add(new Product(203, "LED Desk Lamp", "Furniture", 34.99));

    }
    @Override
    public List<Product> getAll(){
        return this.products;
    }
    @Override
    public List<Product> getByName(String name){
        List<Product> results = new ArrayList<>();

        for(Product p :products){
            if(p.getName().toLowerCase().contains(name.toLowerCase())){
                results.add(p);
            }
        }
        return  results;
    }
   @Override
    public void add(Product product){
        this.products.add(product);
   }
   @Override
    public void remove (Product product){
        this.products.remove(product);
   }


}
