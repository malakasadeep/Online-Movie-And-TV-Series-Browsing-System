package movie.Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import movie.admin.DBConnect;

public class PackageDBUtil {

    private static final String INSERT_PKG_SQL = "INSERT INTO packages (PkgName, Description, Price, Duration, VideoQuality, PkgType) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PKG_BY_ID_SQL = "SELECT * FROM packages WHERE ID=?";
    private static final String SELECT_ALL_PKGS_SQL = "SELECT * FROM packages";
    private static final String UPDATE_PKG_SQL = "UPDATE packages SET PkgName=?, Description=?, Price=?, Duration=?, VideoQuality=?, PkgType=? WHERE ID=?";
    private static final String DELETE_PKG_SQL = "DELETE FROM packages WHERE ID=?";

    public void insertPackage(Package pkg) throws SQLException {
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_PKG_SQL)) {
            preparedStatement.setString(1, pkg.getPkgName());
            preparedStatement.setString(2, pkg.getDescription());
            preparedStatement.setDouble(3, pkg.getPrice());
            preparedStatement.setInt(4, pkg.getDuration());
            preparedStatement.setString(5, pkg.getVideoQuality());
            preparedStatement.setString(6, pkg.getPkgType());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updatePackage(Package pkg) throws SQLException {
        boolean rowUpdated = false;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_PKG_SQL)) {
            preparedStatement.setString(1, pkg.getPkgName());
            preparedStatement.setString(2, pkg.getDescription());
            preparedStatement.setDouble(3, pkg.getPrice());
            preparedStatement.setInt(4, pkg.getDuration());
            preparedStatement.setString(5, pkg.getVideoQuality());
            preparedStatement.setString(6, pkg.getPkgType());
            preparedStatement.setInt(7, pkg.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public Package selectPackage(int id) {
        Package pkg = null;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_PKG_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String pkgName = rs.getString("PkgName");
                String description = rs.getString("Description");
                double price = rs.getDouble("Price");
                int duration = rs.getInt("Duration");
                String videoQuality = rs.getString("VideoQuality");
                String pkgType = rs.getString("PkgType");

                pkg = new Package(id, pkgName, description, price, duration, videoQuality, pkgType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pkg;
    }

    public List<Package> selectAllPackages() {
        List<Package> packages = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_PKGS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String pkgName = rs.getString("PkgName");
                String description = rs.getString("Description");
                double price = rs.getDouble("Price");
                int duration = rs.getInt("Duration");
                String videoQuality = rs.getString("VideoQuality");
                String pkgType = rs.getString("PkgType");

                packages.add(new Package(id, pkgName, description, price, duration, videoQuality, pkgType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }

    public boolean deletePackage(int id) throws SQLException {
        boolean rowDeleted = false;
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_PKG_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}

