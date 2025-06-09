/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.AlertDAO;
import dto.Alert;
import dto.User;
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
@WebServlet(name = "AlertListController", urlPatterns = {"/AlertListController"})
public class AlertListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute("USER");

            if (loginUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            AlertDAO dao = new AlertDAO();
            ArrayList<Alert> list = dao.getAlertList(loginUser.getUserID(), loginUser.getRoleID());
            request.setAttribute("alertList", list);

        } catch (Exception e) {
            request.setAttribute("ERROR", "Failed to load alert list: " + e.getMessage());
        }

        request.getRequestDispatcher("alertList.jsp").forward(request, response);
    }

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
