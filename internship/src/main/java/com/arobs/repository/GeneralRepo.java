package com.arobs.repository;

import com.arobs.utils.DataSource;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class GeneralRepo {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public GeneralRepo() {
    }

    public Map<String, Integer> getShoppingCart(int userId) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        String querry = "select t1.item_name, t2.quantity from item as t1, order_details as t2, orderTable as t3\n" +
                "where t1.item_id = t2.item_id and t2.order_number=t3.order_number and t3.user_id=" + userId;
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querry)
        ) {
            Map<String, Integer> shoppingCart = new HashMap<>();
            while (resultSet.next()) {
                shoppingCart.put(resultSet.getString("item_name"), resultSet.getInt("quantity"));
            }
            return shoppingCart;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
