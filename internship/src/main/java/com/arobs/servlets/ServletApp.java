package com.arobs.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class ServletApp extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        String param = req.getParameter("name");

        try {
            pw.println("<html><head>");
            pw.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            pw.println("<title>" + param + "</title></head>");

            pw.println("</html>");
        } finally {
            pw.close();
        }

    }
}
