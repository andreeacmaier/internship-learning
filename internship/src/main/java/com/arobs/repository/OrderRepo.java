package com.arobs.repository;

import com.arobs.entities.Order;
import com.arobs.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public OrderRepo() {
    }

    public Order findById(int orderNumber) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        String querry = "SELECT * FROM orderTable WHERE order_number = " + orderNumber;
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querry)
        ) {
            if (resultSet.next()) {
                return extractOrderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> findAll() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        String querry = "SELECT * FROM orderTable";
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querry)
        ) {
            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                Order order = extractOrderFromResultSet(resultSet);
                orders.add(order);
            }
            return orders;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean save(Order order) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orderTable(order_number, user_id) VALUES (?,?)");
        ) {
            preparedStatement.setInt(1, order.getOrderNumber());
            preparedStatement.setInt(2, order.getUserId());

            int inserted = preparedStatement.executeUpdate();
            if (inserted == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Order extractOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();

        order.setOrderNumber(resultSet.getInt("order_number"));
        order.setUserId(resultSet.getInt("user_id"));

        return order;
    }


}
