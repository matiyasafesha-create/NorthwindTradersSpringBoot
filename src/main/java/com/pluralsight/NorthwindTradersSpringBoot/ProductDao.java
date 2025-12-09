package com.pluralsight.NorthwindTradersSpringBoot;

import java.util.List;

public interface ProductDao {
    public List<Product> getAll();
    public List<Product> getByName(String name);
    public void add(Product product);
    public void remove(Product product);

}
