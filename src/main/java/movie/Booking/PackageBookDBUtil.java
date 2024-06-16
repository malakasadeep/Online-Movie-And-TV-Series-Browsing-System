package movie.Booking;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import movie.admin.DBConnect;

public class PackageBookDBUtil {

    private static final String INSERT_BOOKING_SQL = "INSERT INTO package_bookings (UserName, UserEmail, BookpkgName, BookpkgType, StartDate, Duration, PaymentMethod) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BOOKING_BY_ID_SQL = "SELECT * FROM package_bookings WHERE ID=?";
    private static final String SELECT_ALL_BOOKINGS_SQL = "SELECT * FROM package_bookings";
    private static final String UPDATE_BOOKING_SQL = "UPDATE package_bookings SET UserName=?, UserEmail=?, BookpkgName=?, BookpkgType=?, StartDate=?, Duration=?, PaymentMethod=? WHERE ID=?";
    private static final String DELETE_BOOKING_SQL = "DELETE FROM package_bookings WHERE ID=?";

    public void insertBooking(PackageBooking booking) throws SQLException {
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_BOOKING_SQL)) {
            preparedStatement.setString(1, booking.getUserName());
            preparedStatement.setString(2, booking.getUserEmail());
            preparedStatement.setString(3, booking.getBookpkgName());
            preparedStatement.setString(4, booking.getBookpkgType());
            preparedStatement.setString(5, booking.getStartDate());
            preparedStatement.setInt(6, booking.getDuration());
            preparedStatement.setString(7, booking.getPaymentMethod());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateBooking(PackageBooking booking) throws SQLException {
        boolean rowUpdated = false;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_BOOKING_SQL)) {
            preparedStatement.setString(1, booking.getUserName());
            preparedStatement.setString(2, booking.getUserEmail());
            preparedStatement.setString(3, booking.getBookpkgName());
            preparedStatement.setString(4, booking.getBookpkgType());
            preparedStatement.setString(5, booking.getStartDate());
            preparedStatement.setInt(6, booking.getDuration());
            preparedStatement.setString(7, booking.getPaymentMethod());
            preparedStatement.setInt(8, booking.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public PackageBooking selectBooking(int id) {
    	PackageBooking booking = null;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_BOOKING_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("UserName");
                String userEmail = rs.getString("UserEmail");
                String bookpkgName = rs.getString("BookpkgName");
                String bookpkgType = rs.getString("BookpkgType");
                String startDate = rs.getString("StartDate");
                int duration = rs.getInt("Duration");
                String paymentMethod = rs.getString("PaymentMethod");

                booking = new PackageBooking(id, userName, userEmail, bookpkgName, bookpkgType, startDate, duration, paymentMethod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    public List<PackageBooking> selectAllBookings() {
        List<PackageBooking> bookings = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_BOOKINGS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String userName = rs.getString("UserName");
                String userEmail = rs.getString("UserEmail");
                String bookpkgName = rs.getString("BookpkgName");
                String bookpkgType = rs.getString("BookpkgType");
                String startDate = rs.getString("StartDate");
                int duration = rs.getInt("Duration");
                String paymentMethod = rs.getString("PaymentMethod");

                bookings.add(new PackageBooking(id, userName, userEmail, bookpkgName, bookpkgType, startDate, duration, paymentMethod));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public boolean deleteBooking(int id) throws SQLException {
        boolean rowDeleted = false;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_BOOKING_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}

