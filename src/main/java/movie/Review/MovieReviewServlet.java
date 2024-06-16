package movie.Review;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MovieReviewServlet")
public class MovieReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MovieReviewDBUtil reviewDBUtil;

    public MovieReviewServlet() {
        super();
        this.reviewDBUtil = new MovieReviewDBUtil();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/review_new":
                    showNewReviewForm(request, response);
                    break;
                case "/review_insert":
                    insertReview(request, response);
                    break;
                case "/review_delete":
                    deleteReview(request, response);
                    break;
                case "/review_edit":
                    showEditReviewForm(request, response);
                    break;
                case "/review_update":
                    updateReview(request, response);
                    break;
                case "/review_view":
                    viewReviews(request, response);
                    break;
                default:
                    listReviews(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewReviewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ReviewForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String movie = request.getParameter("movie");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String reviewText = request.getParameter("review");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        MovieReview newReview = new MovieReview(movie, rating, reviewText, name, email);
        reviewDBUtil.insertReview(newReview);
        response.sendRedirect("review_list");
    }

    private void deleteReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        reviewDBUtil.deleteReview(id);
        response.sendRedirect("review_list");
    }

    private void showEditReviewForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        MovieReview existingReview = reviewDBUtil.selectReview(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ReviewForm.jsp");
        request.setAttribute("review", existingReview);
        dispatcher.forward(request, response);
    }

    private void updateReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String movie = request.getParameter("movie");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String reviewText = request.getParameter("review");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        MovieReview updatedReview = new MovieReview(id, movie, rating, reviewText, name, email);
        reviewDBUtil.updateReview(updatedReview);
        response.sendRedirect("review_list");
    }

    private void listReviews(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<MovieReview> listReviews = reviewDBUtil.selectAllReviews();
        request.setAttribute("listReviews", listReviews);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ReviewList.jsp");
        dispatcher.forward(request, response);
    }

    private void viewReviews(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<MovieReview> listReviews = reviewDBUtil.selectAllReviews();
        request.setAttribute("listReviews", listReviews);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Reviews.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
