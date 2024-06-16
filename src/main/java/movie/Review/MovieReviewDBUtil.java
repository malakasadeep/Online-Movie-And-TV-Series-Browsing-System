package movie.Review;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import movie.admin.DBConnect;

public class MovieReviewDBUtil {

    private static final String INSERT_REVIEW_SQL = "INSERT INTO reviews (Movie, Rating, Review, Name, Email) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_REVIEW_BY_ID_SQL = "SELECT * FROM reviews WHERE ID=?";
    private static final String SELECT_ALL_REVIEWS_SQL = "SELECT * FROM reviews";
    private static final String UPDATE_REVIEW_SQL = "UPDATE reviews SET Movie=?, Rating=?, Review=?, Name=?, Email=? WHERE ID=?";
    private static final String DELETE_REVIEW_SQL = "DELETE FROM reviews WHERE ID=?";

    public void insertReview(MovieReview review) throws SQLException {
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_REVIEW_SQL)) {
            preparedStatement.setString(1, review.getMovie());
            preparedStatement.setInt(2, review.getRating());
            preparedStatement.setString(3, review.getReview());
            preparedStatement.setString(4, review.getName());
            preparedStatement.setString(5, review.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateReview(MovieReview review) throws SQLException {
        boolean rowUpdated = false;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_REVIEW_SQL)) {
            preparedStatement.setString(1, review.getMovie());
            preparedStatement.setInt(2, review.getRating());
            preparedStatement.setString(3, review.getReview());
            preparedStatement.setString(4, review.getName());
            preparedStatement.setString(5, review.getEmail());
            preparedStatement.setInt(6, review.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public MovieReview selectReview(int id) {
        MovieReview review = null;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_REVIEW_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String movie = rs.getString("Movie");
                int rating = rs.getInt("Rating");
                String reviewText = rs.getString("Review");
                String name = rs.getString("Name");
                String email = rs.getString("Email");

                review = new MovieReview(id, movie, rating, reviewText, name, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    public List<MovieReview> selectAllReviews() {
        List<MovieReview> reviews = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_REVIEWS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String movie = rs.getString("Movie");
                int rating = rs.getInt("Rating");
                String reviewText = rs.getString("Review");
                String name = rs.getString("Name");
                String email = rs.getString("Email");

                reviews.add(new MovieReview(id, movie, rating, reviewText, name, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public boolean deleteReview(int id) throws SQLException {
        boolean rowDeleted = false;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_REVIEW_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}

