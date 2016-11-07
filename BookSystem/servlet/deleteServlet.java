package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspdb.BookCatalog;
import jspdb.DatabaseBookCatalog;


/**
 * Servlet implementation class deleteServlet
 */
@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BookCatalog catalog = DatabaseBookCatalog.getInstance();
		String inData = request.getParameter("deleteId");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		catalog.deleteBook(inData);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/deleteJsp.jsp");
		dispatcher.forward(request, response);

	}
}
