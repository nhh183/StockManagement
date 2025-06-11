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
import java.sql.SQLException;
import java.text.SimpleDateFormat;


/**
 *
 * @author loan1
 */
@WebServlet(name = "CreateHistoricalPriceController", urlPatterns = {"/CreateHistoricalPriceController"})
public class CreateHistoricalPriceController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
            boolean success = dao.createHistoricalPrice(price);

            if (success) {
                request.setAttribute("MSG", "Historical price added successfully.");
            } else {
                request.setAttribute("ERROR", "Failed to add historical price.");
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Error: " + e.getMessage());
        }
        request.getRequestDispatcher("MainController?action=HistoricalPriceList&ticker=" + request.getParameter("ticker")).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}