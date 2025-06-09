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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author loan1
 */

@WebServlet(name = "SearchAlertController", urlPatterns = {"/SearchAlertController"})
public class SearchAlertController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("USER");

        if (loginUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            String keyword = request.getParameter("keyword") != null ? request.getParameter("keyword").trim() : "";
            String filterDirection = request.getParameter("filterDirection") != null ? request.getParameter("filterDirection").trim() : "";
            String filterStatus = request.getParameter("filterStatus") != null ? request.getParameter("filterStatus").trim() : "";

            AlertDAO dao = new AlertDAO();
            ArrayList<Alert> list = dao.advancedSearchAlert(keyword, filterDirection, filterStatus, loginUser.getUserID(), loginUser.getRoleID());

            request.setAttribute("alertList", list);
            request.setAttribute("keyword", keyword);
            request.setAttribute("filterDirection", filterDirection);
            request.setAttribute("filterStatus", filterStatus);

            if (list.isEmpty()) {
                request.setAttribute("ERROR", "No alerts found matching your criteria.");
            }

        } catch (Exception e) {
            request.setAttribute("ERROR", "An error occurred: " + e.getMessage());
        }

        request.getRequestDispatcher("alertList.jsp").forward(request, response);
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

    @Override
    public String getServletInfo() {
        return "SearchAlertController handles advanced alert searching with filters";
    }
}
