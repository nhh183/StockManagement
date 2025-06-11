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
import java.util.ArrayList;

/**
 *
 * @author loan1
 */
@WebServlet(name = "SearchHistoricalPriceController", urlPatterns = {"/SearchHistoricalPriceController"})
public class SearchHistoricalPriceController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String fromDateStr = request.getParameter("fromDate");
            String toDateStr = request.getParameter("toDate");

            HistoricalPriceDAO dao = new HistoricalPriceDAO();
            ArrayList<HistoricalPrice> list = new ArrayList<>();

            if (fromDateStr != null && toDateStr != null 
                && !fromDateStr.isEmpty() && !toDateStr.isEmpty()) {

                Date fromDate = Date.valueOf(fromDateStr);
                Date toDate = Date.valueOf(toDateStr);
                list = dao.searchHistoricalPrices(fromDate, toDate);
            }

            request.setAttribute("historicalPriceList", list);
            request.setAttribute("fromDate", fromDateStr);
            request.setAttribute("toDate", toDateStr);

        } catch (Exception e) {
            request.setAttribute("ERROR", "Error: " + e.getMessage());
        }
        request.getRequestDispatcher("historicalPriceList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}