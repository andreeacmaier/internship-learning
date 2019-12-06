package com.arobs.servlets;
import com.arobs.entities.Order;
import com.arobs.entities.OrderDetails;
import com.arobs.service.ProductManager;
import com.arobs.entities.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@WebServlet(urlPatterns = "/add")
public class FormPageServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(FormPageServlet.class.getName());
    private ProductManager productManager = null;
    private int orderNumber;

    @Override
    public void init() throws ServletException {
        //todo connection
        productManager = new ProductManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String product = req.getParameter("product");
        String quantityParameter = req.getParameter("quantity");

        if (quantityParameter != null) {
            try {
                HttpSession session = req.getSession(true);
                int quantity = Integer.parseInt(quantityParameter.trim());

                Order order = new Order(254, 1);
                Item item = productManager.getItemByName(product);
                OrderDetails orderDetails = new OrderDetails(item, order, quantity);

                boolean saved = productManager.saveOrderDetail(orderDetails);
                if (saved) {
                    LOGGER.info("Product added in cart.");
                }

                session.setAttribute("productManager", productManager);
                RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/formPageView.jsp");
                dispatcher.forward(req, resp);

                /*if (!elementAlreadyAdded(product, (ArrayList<Item>) productManager.getItems())) {
                    productManager.saveItem(item);
                    session.setAttribute("productManager", productManager);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/formPageView.jsp");
                    dispatcher.forward(req, resp);

                } else {
                    req.setAttribute("errorMessage", "Product is already in the list.");
                    RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/formPageErrorView.jsp");
                    dispatcher.forward(req, resp);

                    LOGGER.error("Product is already in the list.");
                }*/

            } catch (NumberFormatException | ClassNotFoundException numberFormatException) {
                req.setAttribute("errorMessage", "Enter a numeric value for quantity.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/formPageErrorView.jsp");
                dispatcher.forward(req, resp);

                LOGGER.error("Parameter from quantity field is not a number.");
            }
        } else {
            req.setAttribute("errorMessage", "Quantity field cannot be empty.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/formPageErrorView.jsp");
            dispatcher.forward(req, resp);

            LOGGER.error("Parameter from quantity field is null or empty.");
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/formPageView.jsp");
        dispatcher.forward(req, resp);
    }

    private boolean elementAlreadyAdded(String productName, ArrayList<Item> items) {
        for (Item i : items) {
            if (i.getProduct().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    private int getRandomOrderNumber() {
        Random random = new Random(100);
        return random.nextInt();
    }
}