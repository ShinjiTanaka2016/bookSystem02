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
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//	searchFormからの受け取り設定
		request.setCharacterEncoding("UTF-8");
		BookCatalog catalog = DatabaseBookCatalog.getInstance();

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");

		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"booksystemsample.css\" type=\"text/css\">");
		out.println("<title>BookSystem(List)</title>");
		out.println("</head>");

		out.println("<body background=\"/BookSystem/Image/backGround.png\">");
		out.println("<h1>検索結果</h1><br><hr>");

		if(catalog.searchBooks(request.getParameter("search")).length == 0){
			out.println("その検索ワードに当てはまる書籍はありませんでした。<br><br>");
			out.println("<p><a href=\"/BookSystem/SearchForm.html\">"
					+ "<img src=\"/BookSystem/Image/Back.png\" alt=\"Back\" border=\"0\"></a></p>");
		}else{
			out.println("<table><tbody>");
			out.println("<tr>"
					+ "<th><b>ＩＤ</b></th>		<th><b>タイトル</b></th>"
					+ "<th><b>著者</b></th>		<th><b>訳者</b></th>"
					+ "<th><b>出版社</b></th>	<th><b>出版年月日</b></th>"
					+ "<th><b>コード</b></th>	<th><b>備考</b></th>"
					+ "<th><b>状態</b></th>		<th><b>キーワード</b></th>"
					+ "<th><b>登録者</b></th>	<th><b>登録日</b></td></th>");
			for(Book book:catalog.searchBooks(request.getParameter("search"))){

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
			out.println("<p><a href=\"/BookSystem/SearchForm.html\">"
					+ "<img src=\"/BookSystem/Image/Back.png\" border=\"0\"></a></p>");
		}


		//	メニューに戻る
		out.println("<hr><p><a href=\"/BookSystem/index.html\">"
				+ "<img src=\"/BookSystem/Image/BackMenu.png\" alt=\"BackMenu\" border=\"0\"></a></p>");
		out.println("</body>");

		out.println("</html>");
	}
}
