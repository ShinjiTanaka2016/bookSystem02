package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspdb.BookCatalog;
import jspdb.DatabaseBookCatalog;

/**
 * Servlet implementation class addCheckServlet
 */
@WebServlet("/addCheckServlet")
public class addCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
											throws ServletException, IOException
	{
		//	addFormからの受け取り設定
		request.setCharacterEncoding("UTF-8");
		BookCatalog catalog = DatabaseBookCatalog.getInstance();


		//	登録内容を表示
		String bookId = catalog.createUniqueBookId();
		String title= request.getParameter("title");					//	タイトル
		String author= request.getParameter("author");					//	著者
		String translator= request.getParameter("translator");			//	訳者
		String publisher= request.getParameter("publisher");			//	出版社
		String publicationDate=
				  request.getParameter("publicationDateYear") + "-"
				+ request.getParameter("publicationDateMonth") + "-"
				+ request.getParameter("publicationDateDay");			//	出版年月日
		String code= request.getParameter("code");						//	ISBN/ISSN
		String memo= request.getParameter("memo");						//	備考
		String keyword= request.getParameter("keyword");				//	キーワード
		String status= request.getParameter("status");					//	状態
		String detaCreator= request.getParameter("detaCreator");		//	登録者
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataCreatedDate = sdf.format(now);						//	登録日

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"booksystemsample.css\" type=\"text/css\">");
		out.println("<title>BookSystem(AddPage)</title>");
		out.println("</head>");

		out.println("<body background=\"/BookSystem/Image/backGround.png\">");
		out.println("<h1>登録</h1><hr>");
		out.println("下記で登録してよろしいですか？<br><br>");
		out.println("<table><tbody>");
		out.println("<tr><th><b>　ＩＤ</b></th>			<td>" + bookId + "</td></tr>");
		out.println("<tr><th><b>　タイトル</b></th>		<td>" + title + "</td></tr>");
		out.println("<tr><th><b>　著者</b></th>			<td>" + author + "</td></tr>");
		out.println("<tr><th><b>　訳者</b></th>			<td>" + translator + "</td></tr>");
		out.println("<tr><th><b>　出版社</b></th>		<td>" + publisher + "</td></tr>");
		out.println("<tr><th><b>　出版年月日</b></th>	<td>" + publicationDate + "</td></tr>");
		out.println("<tr><th><b>　コード</b></th>		<td>" + code + "</td></tr>");
		out.println("<tr><th><b>　状態</b></th>			<td>" + status + "</td></tr>");
		out.println("<tr><th><b>　備考</b></th>			<td>" + memo +"</td></tr>");
		out.println("<tr><th><b>　登録者</b></th>		<td>" + detaCreator + "</td></tr>");
		out.println("<tr><th><b>　登録日</b></th>		<td>" + dataCreatedDate + "</td></tr>");
		out.println("</tbody></table><br>");


		//	はいの場合登録
		out.println("<form action=\"/BookSystem/AddServlet\" method=\"post\">");
		out.println("<input type=\"hidden\" name=\"bookId\" value=\"" + bookId + "\" >");
		out.println("<input type=\"hidden\" name=\"title\" value=\"" + title + "\" >");
		out.println("<input type=\"hidden\" name=\"author\" value=\"" + author + "\" >");
		out.println("<input type=\"hidden\" name=\"translater\" value=\"" + translator + "\" >");
		out.println("<input type=\"hidden\" name=\"publisher\" value=\"" + publisher + "\" >");
		out.println("<input type=\"hidden\" name=\"publicationDate\" value=\"" + publicationDate + "\" >");
		out.println("<input type=\"hidden\" name=\"code\" value=\"" + code + "\" >");
		out.println("<input type=\"hidden\" name=\"memo\" value=\"" + memo + "\" >");
		out.println("<input type=\"hidden\" name=\"keyword\" value=\"" + keyword + "\" >");
		out.println("<input type=\"hidden\" name=\"status\" value=\"" + status + "\" >");
		out.println("<input type=\"hidden\" name=\"detaCreator\" value=\"" + detaCreator + "\" >");
		out.println("<input type=\"hidden\" name=\"dataCreatedDate\" value=\"" + dataCreatedDate + "\" >");
		out.println("<input type=\"submit\" value=\"はい\">");
		out.println("</form>");

		//	いいえの場合戻る
		out.println("<form action=\"/BookSystem/addForm.html\">");
		out.println("<input type=\"submit\" value=\"いいえ\">");
		out.println("</form>");

		out.println("<hr><a href=\"/BookSystem/index.html\">"
				  + "<img src=\"/BookSystem/Image/BackMenu.png\" alt=\"BackMenu\" border=\"0\"></a>");

		out.println("</body>");

		out.println("</html>");

	}
}
