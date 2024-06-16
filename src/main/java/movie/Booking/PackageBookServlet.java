package movie.Booking;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PackageBookServlet")
public class PackageBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PackageBookDBUtil packageBookDBUtil;

    public PackageBookServlet() {
        super();
        this.packageBookDBUtil = new PackageBookDBUtil();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/book_new":
                    showNewBookingForm(request, response);
                    break;
                case "/book_insert":
                    insertBooking(request, response);
                    break;
                case "/book_delete":
                    deleteBooking(request, response);
                    break;
                case "/book_edit":
                    showEditBookingForm(request, response);
                    break;
                case "/book_update":
                    updateBooking(request, response);
                    break;
                case "/book_view":
                    viewBookings(request, response);
                    break;
                default:
                    listBookings(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewBookingForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookingForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String bookpkgName = request.getParameter("bookpkgName");
        String bookpkgType = request.getParameter("bookpkgType");
        String startDate = request.getParameter("startDate");
        int duration = Integer.parseInt(request.getParameter("duration"));
        String paymentMethod = request.getParameter("paymentMethod");

        PackageBooking newBooking = new PackageBooking(userName, userEmail, bookpkgName, bookpkgType, startDate, duration, paymentMethod);
        packageBookDBUtil.insertBooking(newBooking);
        response.sendRedirect("book_list");
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        packageBookDBUtil.deleteBooking(id);
        response.sendRedirect("book_list");
    }

    private void showEditBookingForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PackageBooking existingBooking = packageBookDBUtil.selectBooking(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookingForm.jsp");
        request.setAttribute("bookpkg", existingBooking);
        dispatcher.forward(request, response);
    }

    private void updateBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String bookpkgName = request.getParameter("bookpkgName");
        String bookpkgType = request.getParameter("bookpkgType");
        String startDate = request.getParameter("startDate");
        int duration = Integer.parseInt(request.getParameter("duration"));
        String paymentMethod = request.getParameter("paymentMethod");

        PackageBooking updatedBooking = new PackageBooking(id, userName, userEmail, bookpkgName, bookpkgType, startDate, duration, paymentMethod);
        packageBookDBUtil.updateBooking(updatedBooking);
        response.sendRedirect("book_list");
    }

    private void listBookings(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<PackageBooking> listBookings = packageBookDBUtil.selectAllBookings();
        request.setAttribute("listBookings", listBookings);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookingList.jsp");
        dispatcher.forward(request, response);
    }

    private void viewBookings(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<PackageBooking> listBookings = packageBookDBUtil.selectAllBookings();
        request.setAttribute("listBookings", listBookings);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookings.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
