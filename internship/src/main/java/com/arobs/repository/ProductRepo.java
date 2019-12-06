package com.arobs.repository;

import com.arobs.entities.Item;
import com.arobs.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public void ProductRepo() {
    }

    public Item findById(int id) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        String querry = "SELECT * FROM item WHERE item_id = " + id;
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querry)
        ) {
            if (resultSet.next()) {
                return extractItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Item findByName(String name) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        String querry = "SELECT * FROM item WHERE item_name = '" + name + "'";
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querry)
        ) {
            if (resultSet.next()) {
                return extractItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> findAll() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        String querry = "SELECT * FROM item";
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querry)
        ) {
            List<Item> items = new ArrayList<>();
            while (resultSet.next()) {
                Item item = extractItemFromResultSet(resultSet);
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Item extractItemFromResultSet(ResultSet resultSet) throws SQLException {
        Item item = new Item();

        item.setItemId(resultSet.getInt("item_id"));
        item.setProduct(resultSet.getString("item_name"));
        item.setStock(resultSet.getInt("stock"));

        return item;
    }
}
