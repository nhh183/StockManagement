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

/**
 *
 * @author User
 */
@WebServlet(name = "CreateTransactionController", urlPatterns = {"/CreateTransactionController"})
public class CreateTransactionController extends HttpServlet {

    private static final String TRANSACTION_LIST_PAGE = "transactionList.jsp";
    private static final String CREATE_TRANSACTION_PAGE = "addTransaction.jsp";

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

        String url = CREATE_TRANSACTION_PAGE;

        User loginUser = (User) request.getSession().getAttribute("LOGIN_USER");
        if (loginUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            String ticker = request.getParameter("ticker");
            String type = request.getParameter("type");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            float price = Float.parseFloat(request.getParameter("price"));
            String status = request.getParameter("status");

            if (ticker.isEmpty() || type.isEmpty() || status.isEmpty()) {
                request.setAttribute("ERROR", "Please fill in all required fields.");
            } else if (quantity <= 0 || price <= 0) {
                request.setAttribute("ERROR", "Quantity and price must be greater than 0.");
            } else if (!type.equals("buy") && !type.equals("sell")) {
                request.setAttribute("ERROR", "Transaction type must be 'buy' or 'sell'.");
            } else if (!status.equals("pending") && !status.equals("executed")) {
                request.setAttribute("ERROR", "Status must be 'pending' or 'executed'.");
            } else {
                TransactionDAO dao = new TransactionDAO();
                Transaction transaction = new Transaction(loginUser.getUserID(), ticker, type, quantity, price, status);

                if (dao.createTrasaction(transaction)) {
                    request.setAttribute("MSG", "Transaction created successfully.");
                    url = TRANSACTION_LIST_PAGE;
                } else {
                    request.setAttribute("ERROR", "Failed to create transaction.");
                }
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "An error occurred: " + e.getMessage());
        }
        request.getRequestDispatcher(url).forward(request, response);

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
