package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import dao.AlertDAO;
import dto.Users;
import dto.Alerts;
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
@WebServlet(urlPatterns={"/DeleteAlertController"})
public class DeleteAlertController extends HttpServlet {
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Users loginUser = (Users) session.getAttribute("LOGIN_USER");

        if (loginUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int alertID = Integer.parseInt(request.getParameter("alertID"));

        try {
            AlertDAO dao = new AlertDAO();
            Alerts alert = dao.getAlertById(alertID);

            if (!loginUser.getUserID().equals(alert.getUserID()) && !"AD".equals(loginUser.getRoleID())) {
                request.setAttribute("ERROR", "Không có quyền.");
            } else if (!"inactive".equals(alert.getStatus())) {
                request.setAttribute("ERROR", "Chỉ được xóa khi trạng thái là inactive.");
            } else {
                boolean success = dao.deleteAlert(alertID);
                if (success) {
                    request.setAttribute("MESSAGE", "Xoá thành công.");
                } else {
                    request.setAttribute("ERROR", "Xoá thất bại.");
                }
            }

            request.getRequestDispatcher("SearchAlertController").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("ERROR", "Lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher("SearchAlertController").forward(request, response);
        }
    }
}