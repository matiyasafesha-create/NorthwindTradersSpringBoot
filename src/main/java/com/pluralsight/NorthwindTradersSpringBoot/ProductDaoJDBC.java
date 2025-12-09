package com.pluralsight.NorthwindTradersSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component

public class ProductDaoJDBC implements ProductDao{


    private List<Product> products;
    private DataSource dataSource;

    @Autowired
    private ProductDaoJDBC(DataSource dataSource){
        this.products = new ArrayList<>();
        this.dataSource = dataSource;

    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    @Override
    public List<Product> getAll() {
        this.products.clear();
        String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                this.products.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4)));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products.");
            e.printStackTrace();
        }

        return this.products;

    }

    @Override
    public List<Product> getByName(String name) {
        List<Product> results = new ArrayList<>();
        String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products WHERE ProductName LIKE ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);





             ResultSet rs = stmt.executeQuery()) {



            while (rs.next()) {
                results.add(new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("UnitPrice"),
                        rs.getDouble("UnitsInStock")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products.");
            e.printStackTrace();
        }

        return results;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public void remove(Product product) {

    }
}




