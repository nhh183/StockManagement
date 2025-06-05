/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.TransactionDAO;
import dto.TransactionDTO;
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
@WebServlet(name = "UpdateTransactionController", urlPatterns = {"/UpdateTransactionController"})
public class UpdateTransactionController extends HttpServlet {

    private static final String TRANSACTION_LIST_PAGE = "transactionList.jsp";
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
        String url=UPDATE_TRANSACTION_PAGE;

        try {
            User loginUser = (User) request.getSession().getAttribute("LOGIN_USER");
            if (loginUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            int transactionID = Integer.parseInt(request.getParameter("transactionID"));
            TransactionDAO dao = new TransactionDAO();
            TransactionDTO transaction = dao.getTransactionByID(transactionID);
            if (transaction == null) {
                request.setAttribute("MSG", "Transaction is not available!");
                url=TRANSACTION_LIST_PAGE;
            } else {
                if (!transaction.getUserID().equals(loginUser.getUserID()) && !loginUser.getRoleID().equals("AD")) {
                    request.setAttribute("MSG", "Only admin can update transaction status");
                    url=TRANSACTION_LIST_PAGE;
                } else {
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    float price = Float.parseFloat(request.getParameter("price"));
                    String status = request.getParameter("status");

                    if (quantity <= 0 || price <= 0) {
                        request.setAttribute("MSG", "Số lượng và giá phải lớn hơn 0!");
                    } else if (!status.equals("pending") && !status.equals("executed")) {
                        request.setAttribute("MSG", "Trạng thái không hợp lệ!");
                    } else {
                        transaction.setQuantity(quantity);
                        transaction.setPrice(price);
                        transaction.setStatus(status);
                        if (dao.updateTransaction(transaction)) {
                            request.setAttribute("MSG", "Cập nhật thành công!");
                            url=TRANSACTION_LIST_PAGE;
                        } else {
                            request.setAttribute("MSG", "Cập nhật thất bại!");
                        }
                    }

                }
            }

            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            
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
