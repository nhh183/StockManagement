/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;
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
@WebServlet(name="CreateAlertController", urlPatterns={"/CreateAlertController"})
public class CreateAlertController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Users loginUser = (Users) session.getAttribute("LOGIN_USER");

        if (loginUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String ticker = request.getParameter("ticker");
        String direction = request.getParameter("direction");
        String status = "inactive";
        double threshold;

        try {
            threshold = Double.parseDouble(request.getParameter("threshold"));
            if (threshold <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            request.setAttribute("ERROR", "Ngưỡng phải là số dương.");
            request.getRequestDispatcher("addAlert.jsp").forward(request, response);
            return;
        }

        if (!direction.equals("increase") && !direction.equals("decrease")) {
            request.setAttribute("ERROR", "Hướng không hợp lệ.");
            request.getRequestDispatcher("addAlert.jsp").forward(request, response);
            return;
        }

        try {
            Alerts alert = new Alerts(0, loginUser.getUserID(), ticker, threshold, direction, status);
            boolean success = new AlertDAO().createAlert(alert);

            if (success) {
                request.setAttribute("MESSAGE", "Thành công.");
                response.sendRedirect("SearchAlertController");
            } else {
                request.setAttribute("ERROR", "Thất bại.");
                request.getRequestDispatcher("addAlert.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher("addAlert.jsp").forward(request, response);
        }
    }
}
