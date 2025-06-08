/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author NHH
 */
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String LOGIN = "login";
    private final String LOGOUT = "logout";
    private final String SEARCH = "search";
    private final String CREATE = "create";
    private final String UPDATE = "update";
    private final String DELETE = "delete";

    private final String SEARCH_TRANSACTION = "SearchTransaction";
    private final String CREATE_TRANSACTION = "CreateTransaction";
    private final String UPDATE_TRANSACTION = "UpdateTransaction";
    private final String DELETE_TRANSACTION = "DeleteTransaction";
    private final String TRANSACTION_LIST = "TransactionList";

    private final String UPDATE_ALERT = "UpdateAlert";
    private final String DELETE_ALERT = "DeleteAlert";
    private final String CREATE_ALERT = "CreateAlert";

    private final String LOGIN_CONTROLLER = "LoginController";
    private final String LOGOUT_CONTROLLER = "LogoutController";

    private final String SEARCH_CONTROLLER = "SearchController";
    private final String CREATE_CONTROLLER = "CreateController";
    private final String UPDATE_CONTROLLER = "UpdateController";
    private final String DELETE_CONTROLLER = "DeleteController";

    private final String TRANSACTION_LIST_CONTROLLER = "TransactionListController";
    private final String SEARCH_TRANSACTION_CONTROLLER = "SearchTransactionController";
    private final String CREATE_TRANSACTION_CONTROLLER = "CreateTransactionController";
    private final String UPDATE_TRANSACTION_CONTROLLER = "UpdateTransactionController";
    private final String DELETE_TRANSACTION_CONTROLLER = "DeleteTransactionController";

    private final String SEARCH_ALERT_CONTROLLER = "SearchAlertController";
    private final String CREATE_ALERT_CONTROLLER = "CreateAlertController";
    private final String UPDATE_ALERT_CONTROLLER = "UpdateAlertController";
    private final String DELETE_ALERT_CONTROLLER = "DeleteAlertController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "login.jsp";
        try {
            String action = request.getParameter("action");
            switch (action) {
                case LOGIN:
                    url = LOGIN_CONTROLLER;
                    break;
                case LOGOUT:
                    url = LOGOUT_CONTROLLER;
                    break;
                case SEARCH:
                    url = SEARCH_CONTROLLER;
                    break;
                case CREATE:
                    url = CREATE_CONTROLLER;
                    break;
                case UPDATE:
                    url = UPDATE_CONTROLLER;
                    break;
                case DELETE:
                    url = DELETE_CONTROLLER;
                    break;
                case TRANSACTION_LIST:
                    url = TRANSACTION_LIST_CONTROLLER;
                    break;
                case SEARCH_TRANSACTION:
                    url = SEARCH_TRANSACTION_CONTROLLER;
                    break;
                case CREATE_TRANSACTION:
                    url = CREATE_TRANSACTION_CONTROLLER;
                    break;
                case UPDATE_TRANSACTION:
                    url = UPDATE_TRANSACTION_CONTROLLER; // Assuming UPDATE_CONTROLLER handles both normal and transaction updates
                    break;
                case DELETE_TRANSACTION:
                    url = DELETE_TRANSACTION_CONTROLLER;
                    break;
                case CREATE_ALERT:
                    url = CREATE_ALERT_CONTROLLER;
                    break;
                case UPDATE_ALERT:
                    url = UPDATE_ALERT_CONTROLLER;
                    break;
                case DELETE_ALERT:
                    url = DELETE_ALERT_CONTROLLER;
                    break;
            }

        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
