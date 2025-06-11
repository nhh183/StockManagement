package controller_business_rating;

import dao_business_rating.RatingDAO;
import dto.User;
import dto_business_rating.Rating;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@WebServlet(name = "UpdateRatingController", urlPatterns = {"/UpdateRatingController"})
public class UpdateRatingController extends HttpServlet {

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
        if (request.getSession().getAttribute("USER") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        request.setCharacterEncoding("UTF-8");
        String isFind = request.getParameter("isFind");
        try {
            String userID = request.getParameter("userID");
            String ticker = request.getParameter("ticker");
            int score = Integer.parseInt(request.getParameter("score"));
            String note = request.getParameter("note");
            Rating newRating = new Rating(userID, ticker, score, note, LocalDateTime.now());
            
            boolean updated = dao.updateRating(newRating);
            System.out.println("hhehe");
            if (updated) {
                request.setAttribute("MSG", "Updated successfully!");
            } else {
                request.setAttribute("MSG", "Updated failed!");
            }
            request.setAttribute("search", isFind);
            request.getRequestDispatcher("ratingList.jsp").forward(request, response);
        } catch (Exception e) {
            log("Update error", e);
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi khi cập nhật!");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
