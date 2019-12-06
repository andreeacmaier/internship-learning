package com.arobs.repository;

import com.arobs.entities.OrderDetails;
import com.arobs.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsRepo {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String querry;

    public OrderDetailsRepo() {
    }

    public OrderDetails findById(int id) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        querry = "SELECT * FROM order_details WHERE id = " + id;
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querry)
        ) {
            if (resultSet.next()) {
                return extractOrderDetailsFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrderDetails> findAll() throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        querry = "SELECT * FROM order_details";
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querry)
        ) {
            List<OrderDetails> orderDetailsList = new ArrayList<>();
            while (resultSet.next()) {
                OrderDetails orderDetails = extractOrderDetailsFromResultSet(resultSet);
                orderDetailsList.add(orderDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean save(OrderDetails orderDetails) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO order_details(item_id,order_number,quantity) VALUES (?,?,?)");
        ) {
            preparedStatement.setInt(1, orderDetails.getItemId());
            preparedStatement.setInt(2, orderDetails.getOrderNumber());
            preparedStatement.setInt(3, orderDetails.getQuantity());

            int inserted = preparedStatement.executeUpdate();
            if (inserted == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private OrderDetails extractOrderDetailsFromResultSet(ResultSet resultSet) throws SQLException {
        OrderDetails orderDetails = new OrderDetails();

        orderDetails.setId(resultSet.getInt("id"));
        orderDetails.setOrderNumber(resultSet.getInt("order_number"));
        orderDetails.setItemId(resultSet.getInt("item_id"));
        orderDetails.setQuantity(resultSet.getInt("quantity"));

        return orderDetails;
    }
}
