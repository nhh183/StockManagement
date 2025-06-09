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

/**
 *
 * @author loan1
 */
@WebServlet(name="DeleteAlertController", urlPatterns={"/DeleteAlertController"})
public class DeleteAlertController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int alertID = Integer.parseInt(request.getParameter("alertID"));
        HttpSession session = request.getSession();
        String userID = ((dto.User) session.getAttribute("USER")).getUserID();

        AlertDAO dao = new AlertDAO();
        Alert alert = dao.getAlertById(alertID);
        if (!alert.getUserID().equals(userID)) {
            request.setAttribute("ERROR", "Permission denied.");
        } else if (!alert.getStatus().equals("inactive")) {
            request.setAttribute("ERROR", "Only inactive alerts can be deleted.");
        } else {
            try {
                dao.delete(alertID);
                request.setAttribute("MESSAGE", "Alert deleted successfully.");
            } catch (Exception e) {
                request.setAttribute("ERROR", "Delete failed.");
            }
        }

        request.setAttribute("alerts", dao.getAlertList(userID));
        request.getRequestDispatcher("alertList.jsp").forward(request, response);
    }

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}