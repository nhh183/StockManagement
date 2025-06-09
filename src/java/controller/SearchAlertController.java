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

        try {
            HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute("USER");

            if (loginUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String userID = loginUser.getUserID();
            String query = request.getParameter("search") != null ? request.getParameter("search") : "";
            String direction = request.getParameter("direction") != null ? request.getParameter("direction") : "";
            String status = request.getParameter("status") != null ? request.getParameter("status") : "";

            // Lưu thông tin tìm kiếm để hiển thị lại
            session.setAttribute("QUERY", query);
            session.setAttribute("DIRECTION", direction);
            session.setAttribute("STATUS", status);

            // Lấy danh sách cảnh báo
            AlertDAO dao = new AlertDAO();
            ArrayList<Alert> alerts = dao.search(userID, query, direction, status);

            // Gán danh sách vào session
            session.setAttribute("alerts", alerts);

            // Chuyển tiếp về trang JSP
            request.getRequestDispatcher("alertList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Trang báo lỗi nếu cần
        }
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