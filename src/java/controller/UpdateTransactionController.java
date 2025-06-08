/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.TransactionDAO;
import dto.Transaction;
import dto.User;
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
 * @author User
 */
@WebServlet(name = "UpdateTransactionController", urlPatterns = {"/UpdateTransactionController"})
public class UpdateTransactionController extends HttpServlet {

    private static final String TRANSACTION_LIST_CONTROLLER = "TransactionListController";
    private static final String UPDATE_TRANSACTION_PAGE = "updateTransaction.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = UPDATE_TRANSACTION_PAGE;
        HttpSession session = request.getSession();

        try {
            User loginUser = (User) request.getSession().getAttribute("USER");
            if (loginUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            int id = Integer.parseInt(request.getParameter("id"));
            TransactionDAO dao = new TransactionDAO();
            Transaction transaction = dao.getTransactionByID(id);
            if (transaction == null) {
                session.setAttribute("ERROR", "Transaction is not available!");
                url = TRANSACTION_LIST_CONTROLLER;
            } else {
                if (!transaction.getUserID().equals(loginUser.getUserID()) && !loginUser.getRoleID().equals("AD")) {
                    session.setAttribute("ERROR", "You are not authorized to perform this action.");
                    url = TRANSACTION_LIST_CONTROLLER;
                } else {
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    float price = Float.parseFloat(request.getParameter("price"));
                    String status = request.getParameter("status");

                    if (quantity <= 0) {
                        request.setAttribute("ERROR", "Quantity must be greater than 0!");
                    } else if (price <= 0) {
                        request.setAttribute("ERROR", "Price must be greater than 0!");
                    } else if (!status.equals("pending") && !status.equals("executed")) {
                        request.setAttribute("ERROR", "Status must be 'pending' or 'executed'!");
                    } else {
                        transaction.setQuantity(quantity);
                        transaction.setPrice(price);
                        transaction.setStatus(status);
                        if (dao.updateTransaction(transaction)) {
                            session.setAttribute("MSG", "Transaction updated successfully!");
                            url = TRANSACTION_LIST_CONTROLLER;
                        } else {
                            request.setAttribute("ERROR", "Update Transaction failed!");
                        }
                    }

                }
            }

        } catch (Exception e) {
            session.setAttribute("ERROR", "An error occurred: " + e.getMessage());
            url = TRANSACTION_LIST_CONTROLLER;
        }

        if (url.equals(UPDATE_TRANSACTION_PAGE)) {
            request.getRequestDispatcher(url).forward(request, response);
        } else {
            response.sendRedirect(url);
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
        String url = UPDATE_TRANSACTION_PAGE;
        HttpSession session = request.getSession();
        try {
            User loginUser = (User) request.getSession().getAttribute("USER");
            if (loginUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            int id = Integer.parseInt(request.getParameter("id"));
            TransactionDAO dao = new TransactionDAO();
            Transaction transaction = dao.getTransactionByID(id);
            if (transaction == null) {
                session.setAttribute("ERROR", "Transaction is not availble!");
                url = TRANSACTION_LIST_CONTROLLER;
            } else {
                request.setAttribute("TRANSACTION", transaction);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(url).forward(request, response);
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
