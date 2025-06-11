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

/**
 *
 * @author LENOVO
 */
@WebServlet(name="CreateUserController", urlPatterns={"/CreateUserController"})
public class CreateUserController extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       String userID = request.getParameter("userID");
        String fullName = request.getParameter("fullName");
        String roleID = request.getParameter("roleID");
        String password = request.getParameter("password");
        
        User user = new User(userID, fullName, roleID, password);
        try {
            userDAO.createUser(user); // thêm phương thức create vào UserDAO
            request.setAttribute("MSG", "User Created Successfully!");
            // Load lại danh sách
        request.getRequestDispatcher("userList.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("MSG", "User Created Failed!");
        }

        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
