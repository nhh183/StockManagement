/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "SearchUserController", urlPatterns = {"/SearchUserController"})
public class SearchUserController extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy tham số search nếu có
        String search = request.getParameter("search");
        ArrayList<User> userList;
        try {
            if (search != null && !search.trim().isEmpty()) {
                userList = userDAO.searchUser(search.trim());
            } else {
                userList = userDAO.getAllUsers(); // nếu không search thì get all
            }

            request.setAttribute("listUser", userList);
            request.setAttribute("search", search); // để giữ lại value trong ô input
            request.getRequestDispatcher("/userList.jsp").forward(request, response);
        } catch (Exception e) {
            log("Error at SearchUserController: " + e.getMessage(), e);
            throw new ServletException("Lỗi khi tìm user", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
