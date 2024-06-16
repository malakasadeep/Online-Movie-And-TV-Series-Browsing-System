package movie.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminLoginServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ruid = request.getParameter("rusername");
		String rpwd = request.getParameter("rpassword");
		HttpSession session = request.getSession();
		
		
		Connection con = null;
		RequestDispatcher dispatcher = null;
		
		try {
			
			
			con = DBConnect.getConnection();
			
			PreparedStatement pst = con.prepareStatement("select * from admin where uid = ? and password = ?");
			pst.setString(1, ruid);
			pst.setString(2, rpwd);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
				session.setAttribute("id", rs.getString("id"));
				dispatcher = request.getRequestDispatcher("adminHome.jsp");
			}else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("adminLogin.jsp");
			}
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
