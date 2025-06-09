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

/**
 *
 * @author loan1
 */
@WebServlet(name = "DeleteAlertController", urlPatterns = {"/DeleteAlertController"})
public class DeleteAlertController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("USER");

        try {
            int alertID = Integer.parseInt(request.getParameter("id"));
            AlertDAO dao = new AlertDAO();

            if (!dao.canEditOrDeleteAlert(alertID, loginUser.getUserID())) {
                request.setAttribute("ERROR", "You do not have permission to delete this alert.");
            } else {
                boolean deleted = dao.deleteAlert(alertID);
                if (deleted) {
                    request.setAttribute("MSG", "Alert deleted successfully.");
                } else {
                    request.setAttribute("ERROR", "Failed to delete alert.");
                }
            }

            ArrayList<Alert> alertList = dao.getAlertList(loginUser.getUserID(), loginUser.getRoleID());
            request.setAttribute("alertList", alertList);

            request.getRequestDispatcher("alertList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "System error.");
            request.getRequestDispatcher("alertList.jsp").forward(request, response);
        }
    }
}