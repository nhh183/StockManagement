package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import dao.AlertDAO;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loan1
 */
@WebServlet(name="UpdateAlertController", urlPatterns={"/UpdateAlertController"})
public class UpdateAlertController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int alertID = Integer.parseInt(request.getParameter("alertID"));
        float threshold = Float.parseFloat(request.getParameter("threshold"));
        String direction = request.getParameter("direction");
        String status = request.getParameter("status");

        AlertDAO dao = new AlertDAO();
        Alert alert = dao.getAlertById(alertID);
        HttpSession session = request.getSession();
        String userID = ((dto.User) session.getAttribute("USER")).getUserID();

        if (!alert.getUserID().equals(userID)) {
            request.setAttribute("ERROR", "Permission denied.");
            request.getRequestDispatcher("alertList.jsp").forward(request, response);
        } else if (!alert.getStatus().equals("inactive")) {
            request.setAttribute("ERROR", "Only inactive alerts can be edited.");
            request.getRequestDispatcher("alertList.jsp").forward(request, response);
        } else {
            try {
                alert.setThreshold(threshold);
                alert.setDirection(direction);
                alert.setStatus(status);
                dao.update(alert);
                request.setAttribute("MESSAGE", "Alert updated successfully.");
                request.setAttribute("alerts", dao.getAlertList(userID));
                request.getRequestDispatcher("alertList.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("ERROR", "Update failed.");
                request.getRequestDispatcher("alertList.jsp").forward(request, response);
            }
        }
    }

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int alertID = Integer.parseInt(request.getParameter("alertID"));
        Alert alert = new AlertDAO().getAlertById(alertID);
        request.setAttribute("alert", alert);
        request.getRequestDispatcher("updateAlert.jsp").forward(request, response);
    }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}