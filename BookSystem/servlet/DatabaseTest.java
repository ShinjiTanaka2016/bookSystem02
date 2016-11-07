package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseTest
 */
@WebServlet("/DatabaseTest")
public class DatabaseTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sql = "SELECT * FROM bookcatalog";

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>データベーステスト</title>");
		out.println("</head>");
		out.println("<body background=\"/BookSystem/Image/backGround.png\">");

		String msg = "";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			msg = "ドライバのロードに成功しました";
		}catch(ClassNotFoundException e){
			msg = "ドライバのロードに失敗しました①";
		}catch(Exception e){
			msg = "ドライバのロードに失敗しました②";
		}

		out.println("<p>");
		out.println(msg);
		out.println("</p>");


		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost/booksystem","root","utsystem2016");

			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			out.println("<hr>");

			while(rs.next()){
				out.println("bookid:" 			+ rs.getString("bookid") 			+ "<br>");
				out.println("title:" 			+ rs.getString("title") 			+ "<br>");
				out.println("author:"			+ rs.getString("author") 			+ "<br>");
				out.println("translator:" 		+ rs.getString("translator") 		+ "<br>");
				out.println("publisher:" 		+ rs.getString("publisher") 		+ "<br>");
				out.println("publishingdate:" 	+ rs.getString("publishingdate") 	+ "<br>");
				out.println("codeid:" 			+ rs.getString("codeid") 			+ "<br>");
				out.println("memo:" 			+ rs.getString("memo") 				+ "<br>");
				out.println("keyword:" 			+ rs.getString("keyword") 			+ "<br>");
				out.println("statusid:" 		+ rs.getString("statusid") 			+ "<br>");
				out.println("datacreator:" 		+ rs.getString("datacreator")		+ "<br>");
				out.println("datacreateddate:" 	+ rs.getString("datacreateddate") 	+ "<br><hr>");
			}

			msg = "接続成功しました";
			out.println("<p>");
			out.println(msg);
			out.println("</p>");
		}catch(SQLException e){
			e.printStackTrace();
			msg = "接続失敗しました";
			out.println(msg);
			out.println("</p>");
		}finally{
			if(con != null){
				try{
					con.close();
					msg = "接続終了しました";
					out.println("<p>");
					out.println(msg);
					out.println("</p>");
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		out.println("</body>");
		out.println("</html>");
	}
}
