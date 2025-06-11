package controller_business_rating;

import dao_business_rating.RatingDAO;
import dto_business_rating.Rating;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@WebServlet(name = "CreateRatingController", urlPatterns = {"/CreateRatingController"})
public class CreateRatingController extends HttpServlet {

    private RatingDAO dao = new RatingDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userID = request.getParameter("userID");
            String ticker = request.getParameter("ticker");
            int score = Integer.parseInt(request.getParameter("score"));
            String note = request.getParameter("note");
            Rating newRating = new Rating(userID, ticker, score, note, LocalDateTime.now());
            
            dao.createRating(newRating); // thêm phương thức create vào UserDAO
            request.setAttribute("MSG", "Rating Created Successfully!");
        } catch (Exception e) {
            request.setAttribute("MSG", "Rating Created Failed!");
        }
        // Load lại danh sách
        request.getRequestDispatcher("ratingList.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
