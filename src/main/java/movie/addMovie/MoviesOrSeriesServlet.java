package movie.addMovie;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/MoviesOrSeriesServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class MoviesOrSeriesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MovieOrSeriesDBUtill movieOrSeriesDBUtil;

    public MoviesOrSeriesServlet() {
        super();
        this.movieOrSeriesDBUtil = new MovieOrSeriesDBUtill();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/movie_new":
                    showNewMovieForm(request, response);
                    break;
                case "/movie_insert":
                    insertMovie(request, response);
                    break;
                case "/movie_delete":
                    deleteMovie(request, response);
                    break;
                case "/movie_edit":
                    showEditMovieForm(request, response);
                    break;
                case "/movie_update":
                    updateMovie(request, response);
                    break;
                case "/movie_view":
                	viewMovies(request, response);
                    break;
                default:
                    listMovies(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewMovieForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("movieForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertMovie(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // Get other movie details from request parameters
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String releaseDate = request.getParameter("releaseDate");
        String director = request.getParameter("director");
        String description = request.getParameter("description");

        // Define the save directory for images
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + "images";

        // Create the save directory if it does not exist
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        // Initialize imageUrl to empty string
        String imageUrl = "";

        // Iterate through the parts of the multipart request
        for (Part part : request.getParts()) {
            // Extract the file name from the part
            String fileName = extractFileName(part);
            // If the file name is not empty, save the file to the images directory
            if (!fileName.equals("")) {
                part.write(savePath + File.separator + fileName);
                imageUrl = "images" + File.separator + fileName; // Set the imageUrl to the path where the file is saved
            }
        }

        // Create a new MovieOrSeries object with the retrieved details and imageUrl
        MovieOrSeries newMovie = new MovieOrSeries(title, genre, releaseDate, director, description, imageUrl);
        // Insert the new movie into the database
        movieOrSeriesDBUtil.insertMovieOrSeries(newMovie);
        // Redirect to the movie list page
        response.sendRedirect("movie_list");
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    private void deleteMovie(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        movieOrSeriesDBUtil.deleteMovieOrSeries(id);
        response.sendRedirect("movie_list");
    }

    private void showEditMovieForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        MovieOrSeries existingMovie = movieOrSeriesDBUtil.selectMovieOrSeries(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("movieForm.jsp");
        request.setAttribute("movieOrSeries", existingMovie);
        dispatcher.forward(request, response);
    }

    private void updateMovie(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String releaseDate = request.getParameter("releaseDate");
        String director = request.getParameter("director");
        String description = request.getParameter("description");

        // Get the file part from the request
        Part filePart = request.getPart("image");

        // Get the file name
        String fileName = extractFileName(filePart);

        // Get the directory to save the file
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + "images";

        // Create the save directory if it does not exist
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String imageUrl = "";

        // Process and save the file if it exists
        if (!fileName.isEmpty()) {
            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
            String filePath = savePath + File.separator + uniqueFileName;
            filePart.write(filePath);
            imageUrl =  "/images/" + uniqueFileName; // Store the relative path
        } else {
            // If no new image is uploaded, retain the existing image URL
            imageUrl = request.getParameter("imageUrl");
        }

        // Create the updated movie object
        MovieOrSeries updatedMovie = new MovieOrSeries(id, title, genre, releaseDate, director, description, imageUrl);

        // Update the movie in the database
        movieOrSeriesDBUtil.updateMovieOrSeries(updatedMovie);

        // Redirect to the movie list page
        response.sendRedirect("movie_list");
    }

    // Helper method to extract the file name from a part


 // Inside listMovies method
    private void listMovies(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<MovieOrSeries> listMovies = movieOrSeriesDBUtil.selectAllMoviesOrSeries();
        request.setAttribute("listMovies", listMovies);
        // No need to modify image URLs here
        RequestDispatcher dispatcher = request.getRequestDispatcher("movieList.jsp");
        dispatcher.forward(request, response);
    }
    
    private void viewMovies(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<MovieOrSeries> listMovies = movieOrSeriesDBUtil.selectAllMoviesOrSeries();
        request.setAttribute("listMovies", listMovies);
        // No need to modify image URLs here
        RequestDispatcher dispatcher = request.getRequestDispatcher("movies.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
