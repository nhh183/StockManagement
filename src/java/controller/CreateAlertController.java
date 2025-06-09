/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;
import dao.AlertDAO;
import dao.StockDAO;
import dto.User;
import dto.Alert;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author loan1
 */
@WebServlet(name = "CreateAlertController", urlPatterns = {"/CreateAlertController"})
public class CreateAlertController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            User loginUser = (User) session.getAttribute("USER");

            if (loginUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String ticker = request.getParameter("ticker").trim().toUpperCase();
            String thresholdStr = request.getParameter("threshold").trim();
            String direction = request.getParameter("direction").trim();
            String status = request.getParameter("status").trim();

            if (ticker.isEmpty() || thresholdStr.isEmpty() || direction.isEmpty() || status.isEmpty()) {
                session.setAttribute("ERROR", "All fields are required.");
                response.sendRedirect("MainController?action=AlertList");
                return;
            }

            float threshold = Float.parseFloat(thresholdStr);

            StockDAO stockDAO = new StockDAO();
            if (!stockDAO.isTickerExist(ticker)) {
                session.setAttribute("ERROR", "Ticker does not exist in stock list.");
                response.sendRedirect("MainController?action=AlertList");
                return;
            }

            String paddedTicker = String.format("%-6s", ticker);

            Alert alert = new Alert(0, loginUser.getUserID(), paddedTicker, threshold, direction, status);

            AlertDAO dao = new AlertDAO();
            boolean success = dao.createAlert(alert);

            if (success) {
                session.setAttribute("MSG", "Alert created successfully!");
            } else {
                session.setAttribute("ERROR", "Failed to create alert (DB error).");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("ERROR", "System error: " + e.getMessage());
        }

        response.sendRedirect("MainController?action=AlertList");
    }
}
