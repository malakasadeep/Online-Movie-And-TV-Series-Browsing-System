package movie.addMovie;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import movie.admin.DBConnect;

public class MovieOrSeriesDBUtill {

    private static final String INSERT_MOVIE_SQL = "INSERT INTO movies (Title, Genre, ReleaseDate, Director, Description, ImageUrl) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_MOVIE_BY_ID_SQL = "SELECT * FROM movies WHERE ID=?";
    private static final String SELECT_ALL_MOVIES_SQL = "SELECT * FROM movies";
    private static final String UPDATE_MOVIE_SQL = "UPDATE movies SET Title=?, Genre=?, ReleaseDate=?, Director=?, Description=?, ImageUrl=? WHERE ID=?";
    private static final String DELETE_MOVIE_SQL = "DELETE FROM movies WHERE ID=?";

    public void insertMovieOrSeries(MovieOrSeries movieOrSeries) throws SQLException {
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_MOVIE_SQL)) {
            preparedStatement.setString(1, movieOrSeries.getTitle());
            preparedStatement.setString(2, movieOrSeries.getGenre());
            preparedStatement.setString(3, movieOrSeries.getReleaseDate());
            preparedStatement.setString(4, movieOrSeries.getDirector());
            preparedStatement.setString(5, movieOrSeries.getDescription());
            preparedStatement.setString(6, movieOrSeries.getImageUrl());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateMovieOrSeries(MovieOrSeries movieOrSeries) throws SQLException {
        boolean rowUpdated = false;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_MOVIE_SQL)) {
            preparedStatement.setString(1, movieOrSeries.getTitle());
            preparedStatement.setString(2, movieOrSeries.getGenre());
            preparedStatement.setString(3, movieOrSeries.getReleaseDate());
            preparedStatement.setString(4, movieOrSeries.getDirector());
            preparedStatement.setString(5, movieOrSeries.getDescription());
            preparedStatement.setString(6, movieOrSeries.getImageUrl());
            preparedStatement.setInt(7, movieOrSeries.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public MovieOrSeries selectMovieOrSeries(int id) {
        MovieOrSeries movieOrSeries = null;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_MOVIE_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("Title");
                String genre = rs.getString("Genre");
                String releaseDate = rs.getString("ReleaseDate");
                String director = rs.getString("Director");
                String description = rs.getString("Description");
                String imageUrl = rs.getString("ImageUrl");

                movieOrSeries = new MovieOrSeries(id, title, genre, releaseDate, director, description, imageUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieOrSeries;
    }

    public List<MovieOrSeries> selectAllMoviesOrSeries() {
        List<MovieOrSeries> moviesOrSeries = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_MOVIES_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("Title");
                String genre = rs.getString("Genre");
                String releaseDate = rs.getString("ReleaseDate");
                String director = rs.getString("Director");
                String description = rs.getString("Description");
                String imageUrl = rs.getString("ImageUrl");

                moviesOrSeries.add(new MovieOrSeries(id, title, genre, releaseDate, director, description, imageUrl));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return moviesOrSeries;
    }

    public boolean deleteMovieOrSeries(int id) throws SQLException {
        boolean rowDeleted = false;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_MOVIE_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}

