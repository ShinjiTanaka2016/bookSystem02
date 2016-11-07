package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspdb.BookCatalog;
import jspdb.DatabaseBookCatalog;

/**
 * Servlet implementation class AllDeleteServlet
 */
@WebServlet("/AllDeleteServlet")
public class AllDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BookCatalog catalog = DatabaseBookCatalog.getInstance();

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		catalog.allDeleteBook();

		out.println("<html>");
		out.println("<head>");
		out.println("<title></title>");
		out.println("<body background=\"/BookSystem/Image/backGround.png\">");
		out.println("全データを削除しました。");


		out.println("<hr><p><a href=\"/BookSystem/index.html\">"
				+ "<img src=\"/BookSystem/Image/BackMenu.png\" alt=\"BackMenu\" border=\"0\"></a></p>");
		out.println("</body>");
		out.println("</html>");
	}
}
