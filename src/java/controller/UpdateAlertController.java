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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loan1
 */
@WebServlet(name="UpdateAlertController", urlPatterns={"/UpdateAlertController"})
public class UpdateAlertController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int alertID = Integer.parseInt(request.getParameter("id"));
            Alert alert = new AlertDAO().getAlertByID(alertID);
            request.setAttribute("ALERT", alert);
            request.getRequestDispatcher("updateAlert.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("alertList.jsp");
        }
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    User loginUser = (User) session.getAttribute("USER");

    try {
        int alertID = Integer.parseInt(request.getParameter("alertID"));
        AlertDAO dao = new AlertDAO();
        Alert currentAlert = dao.getAlertByID(alertID);

        boolean isAllowed = loginUser.getRoleID().equals("AD") ||
                            loginUser.getUserID().equals(currentAlert.getUserID());

        if (!isAllowed) {
            request.setAttribute("ERROR", "You do not have permission to edit this alert.");
        } else {
            String ticker = request.getParameter("ticker").trim();
            float threshold = Float.parseFloat(request.getParameter("threshold"));
            String direction = request.getParameter("direction").trim();
            String status = request.getParameter("status").trim();

            Alert updatedAlert = new Alert(alertID, loginUser.getUserID(), ticker, threshold, direction, status);
            boolean updated = dao.updateAlert(updatedAlert);

            if (updated) {
                request.setAttribute("MSG", "Alert updated successfully.");
            } else {
                request.setAttribute("ERROR", "Update failed.");
            }
        }

        // Lấy lại danh sách alert để hiển thị
        ArrayList<Alert> alertList = dao.getAlertList(loginUser.getUserID(), loginUser.getRoleID());
        request.setAttribute("alertList", alertList);

        // Forward về trang hiển thị danh sách
        request.getRequestDispatcher("alertList.jsp").forward(request, response);

    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("ERROR", "System error or invalid input.");
        request.getRequestDispatcher("alertList.jsp").forward(request, response);
    }
}
}
