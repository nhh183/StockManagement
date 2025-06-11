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
import java.util.ArrayList;

@WebServlet(name = "SearchRatingController", urlPatterns = {"/SearchRatingController"})
public class SearchRatingController extends HttpServlet {

    private RatingDAO dao = new RatingDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy tham số search nếu có
        String isFind = request.getParameter("isFind");
        ArrayList<Rating> ratingList;
        try {
            if (isFind != null && !isFind.trim().isEmpty()) {
                try {
                    // Kiểm tra xem search có phải số hợp lệ
                    int score = Integer.parseInt(isFind.trim());
                    if (score < 0 || score > 10) {
                        request.setAttribute("MSG", "Score must be between 0 and 10!");
                        ratingList = dao.getAllRating();
                    } else {
                        ratingList = dao.Search(isFind.trim());
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("MSG", "Search value must be a valid number!");
                    ratingList = dao.getAllRating();
                }
            } else {
                ratingList = dao.getAllRating();
            }

            request.setAttribute("ratingList", ratingList);
//            request.setAttribute("search", search); // để giữ lại value trong ô input
            request.getRequestDispatcher("/ratingList.jsp").forward(request, response);
        } catch (Exception e) {
            log("Error at searchRating: " + e.getMessage(), e);
            throw new ServletException("Lỗi khi tìm user", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
