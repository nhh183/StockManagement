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
import java.util.List;

/**
 *
 * @author loan1
 */
@WebServlet(urlPatterns={"/SearchAlertController"})
public class SearchAlertController extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Users loginUser = (Users) session.getAttribute("LOGIN_USER");

        if (loginUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String keyword = request.getParameter("keyword") != null ? request.getParameter("keyword") : "";

        try {
            AlertDAO dao = new AlertDAO();
            List<Alerts> list;

            if ("AD".equals(loginUser.getRoleID())) {
                list = dao.searchAll(keyword);
            } else {
                list = dao.getAlertsByUser(loginUser.getUserID(), keyword);
            }

            request.setAttribute("ALERT_LIST", list);
            request.setAttribute("KEYWORD", keyword);
        } catch (Exception e) {
            request.setAttribute("ERROR", "Lỗi khi tìm kiếm: " + e.getMessage());
        }

        request.getRequestDispatcher("alertList.jsp").forward(request, response);
    }
}