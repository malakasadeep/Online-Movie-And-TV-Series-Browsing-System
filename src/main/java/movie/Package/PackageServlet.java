package movie.Package;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PackageServlet")
public class PackageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PackageDBUtil packageDBUtil;

    public PackageServlet() {
        super();
        this.packageDBUtil = new PackageDBUtil();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/pkg_new":
                    showNewPackageForm(request, response);
                    break;
                case "/pkg_insert":
                    insertPackage(request, response);
                    break;
                case "/pkg_delete":
                    deletePackage(request, response);
                    break;
                case "/pkg_edit":
                    showEditPackageForm(request, response);
                    break;
                case "/pkg_update":
                    updatePackage(request, response);
                    break;
                case "/pkg_view":
                	pkgview(request, response);
                    break;
                default:
                    listPackages(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewPackageForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("packageForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertPackage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String pkgName = request.getParameter("pkgName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        String videoQuality = request.getParameter("videoQuality");
        String pkgType = request.getParameter("pkgType");

        Package newPackage = new Package(pkgName, description, price, duration, videoQuality, pkgType);
        packageDBUtil.insertPackage(newPackage);
        response.sendRedirect("pkg_list");
    }

    private void deletePackage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        packageDBUtil.deletePackage(id);
        response.sendRedirect("pkg_list");
    }

    private void showEditPackageForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Package existingPackage = packageDBUtil.selectPackage(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("packageForm.jsp");
        request.setAttribute("pkg", existingPackage);
        dispatcher.forward(request, response);
    }

    private void updatePackage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String pkgName = request.getParameter("pkgName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        String videoQuality = request.getParameter("videoQuality");
        String pkgType = request.getParameter("pkgType");

        Package updatedPackage = new Package(id, pkgName, description, price, duration, videoQuality, pkgType);
        packageDBUtil.updatePackage(updatedPackage);
        response.sendRedirect("pkg_list");
    }

    private void listPackages(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Package> listPackages = packageDBUtil.selectAllPackages();
        request.setAttribute("listPackages", listPackages);
        RequestDispatcher dispatcher = request.getRequestDispatcher("packageList.jsp");
        dispatcher.forward(request, response);
    }
    
    private void pkgview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Package> listPackages = packageDBUtil.selectAllPackages();
        request.setAttribute("listPackages", listPackages);
        RequestDispatcher dispatcher = request.getRequestDispatcher("packages.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
