package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspdb.Book;
import jspdb.BookCatalog;
import jspdb.DatabaseBookCatalog;

/**
 * Servlet implementation class deleteCheckServlet
 */
@WebServlet("/deleteCheckServlet")
public class deleteCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
											throws ServletException, IOException
	{

		request.setCharacterEncoding("UTF-8");
		BookCatalog catalog = DatabaseBookCatalog.getInstance();

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String deleteId = request.getParameter("deleteId");

		out.println("<html>");

		out.println("<head>");
		out.println("<title>BookSystem(List)</title>");
		out.println("</head>");

		out.println("<body background=\"/BookSystem/Image/backGround.png\">");
		out.println("<h1>削除</h1><br><hr>");

		if(catalog.getBook(deleteId) == null){
			out.println("そのIDに当てはまる書籍はありませんでした。<br><br>");
			out.println("<p><a href=\"/BookSystem/deleteForm.html\">"
					+ "<img src=\"/BookSystem/Image/Back.png\" alt=\"Back\" border=\"0\"></a></p>");
		}else{
			out.println("以下の書籍を削除してよろしいですか？<br><br>");

			for(Book book:catalog.searchBooks(deleteId)){
				out.println("<table border=\"1\" cellspacing=\"1\">");
				out.println("<tr><td width=\"110\" style=\"background-color:#fffff0\";>"
						+ "<b>　ＩＤ</b></td><td style=\"background-color:#ffffff\"; width=\"200\">" + book.getBookId() + "</td></tr>");
				out.println("<tr><td style=\"background-color:#fffff0\";>"
						+ "<b>　タイトル</b></td><td style=\"background-color:#ffffff\";>" + book.getTitle() + "</td></tr>");
				out.println("<tr><td style=\"background-color:#fffff0\";>"
						+ "<b>　著者</b></td><td style=\"background-color:#ffffff\";>" + book.getAuthor() + "</td></tr>");
				out.println("<tr><td style=\"background-color:#fffff0\";>"
						+ "<b>　訳者</b></td><td style=\"background-color:#ffffff\";>" + book.getTranslator() + "</td></tr>");
				out.println("<tr><td style=\"background-color:#fffff0\";>"
						+ "<b>　出版社</b></td><td style=\"background-color:#ffffff\";>" + book.getPublisher() + "</td></tr>");
				out.println("<tr><td style=\"background-color:#fffff0\";"
						+ "><b>　出版年月日</b></td><td style=\"background-color:#ffffff\";>" + book.getPublicationDate() + "</td></tr>");
				out.println("<tr><td style=\"background-color:#fffff0\";>"
						+ "<b>　コード</b></td><td style=\"background-color:#ffffff\";>" + book.getCode() + "</td></tr>");
				out.println("<tr><td style=\"background-color:#fffff0\";>"
						+ "<b>　状態</b></td><td style=\"background-color:#ffffff\";>" + book.getStatus() + "</td></tr>");
				out.println("<tr><td style=\"background-color:#fffff0\";>"
						+ "<b>　備考</b></td><td style=\"background-color:#ffffff\";>" + book.getMemo() +"</td></tr>");
				out.println("<tr><td style=\"background-color:#fffff0\";>"
						+ "<b>　登録者</b></td><td style=\"background-color:#ffffff\";>" + book.getDetaCreator() + "</td></tr>");
				out.println("<tr><td style=\"background-color:#fffff0\";>"
						+ "<b>　登録日</b></td><td style=\"background-color:#ffffff\";>" + book.getDataCreatedDate() + "</td></tr>");
				out.println("</table><br>");

			}
			//	はいの場合削除
			out.println("<form action=\"/BookSystem/deleteServlet\" method=\"post\">");
			out.println("<input type=\"hidden\" name=\"deleteId\" value=\"" + deleteId + "\">");
			out.println("<input type=\"submit\" value=\"はい\">");
			out.println("</form>");

			//	いいえの場合戻る
			out.println("<form action=\"/BookSystem/deleteForm.html\">");
			out.println("<input type=\"submit\" value=\"いいえ\">");
			out.println("</form>");

		}

		//	メニューにもどる
		out.println("<hr><p><a href=\"/BookSystem/index.html\">"
				+ "<img src=\"/BookSystem/Image/BackMenu.png\" alt=\"BackMenu\" border=\"0\"></a></p>");
		out.println("</body>");

		out.println("</html>");
	}
}
