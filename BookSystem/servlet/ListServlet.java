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
 * Servlet implementation class ListServlet
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BookCatalog catalog = DatabaseBookCatalog.getInstance();

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");

		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"booksystemsample.css\" type=\"text/css\">");
		out.println("<title>BookSystem(List)</title>");
		out.println("</head>");

		out.println("<body background=\"/BookSystem/Image/backGround.png\">");
		out.println("<h1>リスト</h1><br><hr>");



		if(catalog.getBooks().length == 0){
			out.println("ファイルデータがありません。<br>");
		}
		out.println("<b>合計" + catalog.getBooks().length + "項目</b><br>");
		out.println("<table><tbody>");
		out.println("<tr>"
				+ "<th><b>ＩＤ</b></th>		<th><b>タイトル</b></th>"
				+ "<th><b>著者</b></th>		<th><b>訳者</b></th>"
				+ "<th><b>出版社</b></th>	<th><b>出版年月日</b></th>"
				+ "<th><b>コード</b></th>	<th><b>備考</b></th>"
				+ "<th><b>状態</b></th>		<th><b>キーワード</b></th>"
				+ "<th><b>登録者</b></th>	<th><b>登録日</b></td></th>");
		for(Book book:catalog.getBooks()){

				out.println("<tr>");
				out.println("<td>" + book.getBookId() + "</td>");
				out.println("<td>" + book.getTitle() + "</td>");
				out.println("<td>" + book.getAuthor() + "</td>");
				out.println("<td>" + book.getTranslator() + "</td>");
				out.println("<td>" + book.getPublisher() + "</td>");
				out.println("<td>" + book.getPublicationDate() + "</td>");
				out.println("<td>" + book.getCode() + "</td>");
				out.println("<td>" + book.getMemo() + "</td>");
				out.println("<td>" + book.getStatus() + "</td>");
				out.println("<td>" + book.getKeyword() + "</td>");
				out.println("<td>" + book.getDetaCreator() + "</td>");
				out.println("<td>" + book.getDataCreatedDate() + "</td></tr>");


		}
		out.println("</tbody></table><br>");

		out.println("<hr><a href=\"/BookSystem/index.html\">"
				  + "<img src=\"/BookSystem/Image/BackMenu.png\" alt=\"BackMenu\" border=\"0\"></a>");
		out.println("</body>");

		out.println("</html>");
	}
}
