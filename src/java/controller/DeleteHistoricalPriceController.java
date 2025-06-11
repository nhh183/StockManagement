/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.HistoricalPriceDAO;
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
@WebServlet(name = "DeleteHistoricalPriceController", urlPatterns = {"/DeleteHistoricalPriceController"})
public class DeleteHistoricalPriceController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String ticker = request.getParameter("ticker");
            Date date = Date.valueOf(request.getParameter("date"));

            HistoricalPriceDAO dao = new HistoricalPriceDAO();
            boolean success = dao.deleteHistoricalPrice(ticker, date);

            if (success) {
                request.setAttribute("MSG", "Historical price deleted successfully.");
            } else {
                request.setAttribute("ERROR", "Failed to delete historical price.");
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Error: " + e.getMessage());
        }
        request.getRequestDispatcher("MainController?action=HistoricalPriceList&ticker=" + request.getParameter("ticker")).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}