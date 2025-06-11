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

    private final String LOGIN = "login";
    private final String LOGOUT = "logout";
    private final String SEARCH = "search";
    private final String CREATE = "create";
    private final String UPDATE = "update";
    private final String DELETE = "delete";

    private static final String CREATE_USER = "CreateUser";
    private static final String CREATE_USER_CONTROLLER = "CreateUserController";
    private static final String SEARCH_USER = "SearchUser";
    private static final String SEARCH_USER_CONTROLLER = "SearchUserController";
    private static final String UPDATE_USER = "UpdateUser";
    private static final String UPDATE_USER_CONTROLLER = "UpdateUserController";
    private static final String DELETE_USER = "DeleteUser";
    private static final String DELETE_USER_CONTROLLER = "DeleteUserController";

    private static final String CREATE_RATING = "CreateRating";
    private static final String CREATE_RATING_CONTROLLER = "CreateRatingController";
    private static final String SEARCH_RATING = "SearchRating";
    private static final String SEARCH_RATING_CONTROLLER = "SearchRatingController";
    private static final String UPDATE_RATING = "UpdateRating";
    private static final String UPDATE_RATING_CONTROLLER = "updateRating.jsp";
    private static final String DELETE_RATING = "DeleteRating";
    private static final String DELETE_RATING_CONTROLLER = "DeleteRatingController";

    private final String SEARCH_TRANSACTION = "SearchTransaction";
    private final String CREATE_TRANSACTION = "CreateTransaction";
    private final String UPDATE_TRANSACTION = "UpdateTransaction";
    private final String DELETE_TRANSACTION = "DeleteTransaction";
    private final String TRANSACTION_LIST = "TransactionList";

    private final String UPDATE_ALERT = "UpdateAlert";
    private final String DELETE_ALERT = "DeleteAlert";
    private final String CREATE_ALERT = "CreateAlert";
    private final String SEARCH_ALERT = "SearchAlert";

    private final String UPDATE_HISTOICAL_PRICE = "UpdateHistoricalPrice";
    private final String DELETE_HISTOICAL_PRICE = "DeleteHistoricalPrice";
    private final String CREATE_HISTOICAL_PRICE = "CreateHistoricalPrice";
    private final String SEARCH_HISTOICAL_PRICE = "SearchHistoricalPrice";

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

    private final String ALERT_LIST = "AlertList";
    private final String ALERT_LIST_CONTROLLER = "AlertListController";

    private final String SEARCH_ALERT_CONTROLLER = "SearchAlertController";
    private final String CREATE_ALERT_CONTROLLER = "CreateAlertController";
    private final String UPDATE_ALERT_CONTROLLER = "UpdateAlertController";
    private final String DELETE_ALERT_CONTROLLER = "DeleteAlertController";

    private final String HISTORICAL_PRICE_LIST = "HistoricalPriceList";
    private final String HISTORICAL_PRICE_LIST_CONTROLLER = "HistoricalPriceListController";

    private static final String CREATE_HISTOICAL_PRICE_CONTROLLER = "CreateHistoricalPriceController";
    private static final String DELETE_HISTOICAL_PRICE_CONTROLLER = "DeleteHistoricalPriceController";
    private static final String SEARCH_HISTOICAL_PRICE_CONTROLLER = "SearchHistoricalPriceController";
    private static final String UPDATE_HISTOICAL_PRICE_CONTROLLER = "UpdateHistoricalPriceController";

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
                case SEARCH_USER:
                    url = SEARCH_USER_CONTROLLER;
                    break;
                case CREATE_USER:
                    url = CREATE_USER_CONTROLLER;
                    break;
                case UPDATE_USER:
                    url = "updateUser.jsp";
                    break;
                case DELETE_USER:
                    url = DELETE_USER_CONTROLLER;
                    break;
                case CREATE_RATING:
                    url = CREATE_RATING_CONTROLLER;
                    break;
                case SEARCH_RATING:
                    url = SEARCH_RATING_CONTROLLER;
                    break;
                case UPDATE_RATING:
                    url = UPDATE_RATING_CONTROLLER;
                    break;
                case DELETE_RATING:
                    url = DELETE_RATING_CONTROLLER;
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
                case SEARCH_ALERT:
                    url = SEARCH_ALERT_CONTROLLER;
                    break;
                case ALERT_LIST:
                    url = ALERT_LIST_CONTROLLER;
                    break;
                case CREATE_HISTOICAL_PRICE:
                    url = CREATE_HISTOICAL_PRICE_CONTROLLER;
                    break;
                case DELETE_HISTOICAL_PRICE:
                    url = DELETE_HISTOICAL_PRICE_CONTROLLER;
                    break;
                case UPDATE_HISTOICAL_PRICE:
                    url = UPDATE_HISTOICAL_PRICE_CONTROLLER;
                    break;
                case SEARCH_HISTOICAL_PRICE:
                    url = SEARCH_HISTOICAL_PRICE_CONTROLLER;
                    break;

                case HISTORICAL_PRICE_LIST:
                    url = HISTORICAL_PRICE_LIST_CONTROLLER;
                    break;

            }

        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

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
