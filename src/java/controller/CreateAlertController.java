/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;
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
@WebServlet(name = "CreateAlertController", urlPatterns = {"/CreateAlertController"})
public class CreateAlertController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("USER") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("USER");
        String ticker = request.getParameter("ticker");
        String direction = request.getParameter("direction");
        String strThreshold = request.getParameter("threshold");

        if (ticker == null || direction == null || strThreshold == null ||
            ticker.trim().isEmpty() || direction.trim().isEmpty() || strThreshold.trim().isEmpty()) {
            request.setAttribute("ERROR", "All fields are required.");
            request.getRequestDispatcher("addAlert.jsp").forward(request, response);
            return;
        }

        try {
            float threshold = Float.parseFloat(strThreshold);
            if (threshold <= 0) {
                request.setAttribute("ERROR", "Threshold must be positive.");
                request.getRequestDispatcher("addAlert.jsp").forward(request, response);
                return;
            }

            Alert alert = new Alert(0, user.getUserID(), ticker, threshold, direction, "inactive");
            AlertDAO dao = new AlertDAO();
            dao.create(alert);

            ArrayList<Alert> updatedList = dao.getAlertList(user.getUserID());
            session.setAttribute("alerts", updatedList);
            session.setAttribute("QUERY", "");
            session.setAttribute("DIRECTION", "");
            session.setAttribute("STATUS", "");
            response.sendRedirect("MainController?action=SearchAlert");

        } catch (NumberFormatException e) {
            request.setAttribute("ERROR", "Threshold must be a number.");
            request.getRequestDispatcher("addAlert.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Error creating alert.");
            request.getRequestDispatcher("addAlert.jsp").forward(request, response);
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
