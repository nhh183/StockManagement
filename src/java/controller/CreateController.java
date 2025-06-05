/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.StockDAO;
import dto.Stock;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author NHH
 */
@WebServlet(name="CreateController", urlPatterns={"/CreateController"})
public class CreateController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ticker = request.getParameter("ticker");
        String name = request.getParameter("name");
        String sector = request.getParameter("sector");
        String priceStr = request.getParameter("price");
        String statusStr = request.getParameter("status");

        try {
            float price = Float.parseFloat(priceStr);
            boolean status = Boolean.parseBoolean(statusStr);

            StockDAO dao = new StockDAO();
            if (dao.getStockByTicker(ticker) != null) {
                request.setAttribute("ERROR", "Ticker already exists.");
                request.getRequestDispatcher("addStock.jsp").forward(request, response);
            } else if (price <= 0) {
                request.setAttribute("ERROR", "Price must be positive.");
                request.getRequestDispatcher("addStock.jsp").forward(request, response);
            } else {
                Stock newStock = new Stock(ticker, name, sector, price, status);
                dao.create(newStock);
                request.setAttribute("MESSAGE", "Stock added successfully.");
                request.setAttribute("stocks", dao.getStockList());
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Invalid input.");
            request.getRequestDispatcher("addStock.jsp").forward(request, response);
        }
        } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
