/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.HistoricalPriceDAO;
import dto.HistoricalPrice;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author loan1
 */
@WebServlet(name = "UpdateHistoricalPriceController", urlPatterns = {"/UpdateHistoricalPriceController"})
public class UpdateHistoricalPriceController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String ticker = request.getParameter("ticker");
            String dateStr = request.getParameter("date");
            Date date = Date.valueOf(dateStr);

            HistoricalPriceDAO dao = new HistoricalPriceDAO();
            HistoricalPrice price = dao.getHistoricalPrice(ticker, date);

            request.setAttribute("PRICE", price);
            request.getRequestDispatcher("updateHistoricalPrice.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("MainController?action=HistoricalPriceList");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String ticker = request.getParameter("ticker").trim().toUpperCase();
            Date date = Date.valueOf(request.getParameter("date"));
            float open = Float.parseFloat(request.getParameter("open"));
            float close = Float.parseFloat(request.getParameter("close"));
            float high = Float.parseFloat(request.getParameter("high"));
            float low = Float.parseFloat(request.getParameter("low"));

            HistoricalPrice price = new HistoricalPrice(ticker, date, open, close, high, low);
            HistoricalPriceDAO dao = new HistoricalPriceDAO();
            boolean success = dao.updateHistoricalPrice(price);

            if (success) {
                request.getSession().setAttribute("MSG", "Historical price updated successfully.");
            } else {
                request.getSession().setAttribute("ERROR", "Failed to update historical price.");
            }
            response.sendRedirect("MainController?action=HistoricalPriceList&ticker=" + request.getParameter("ticker"));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("MainController?action=HistoricalPriceList");
        }
    }
}
