package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspdb.Book;
import jspdb.BookCatalog;
import jspdb.DatabaseBookCatalog;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		BookCatalog catalog = DatabaseBookCatalog.getInstance();
		Book book = new Book();


		String bookId= request.getParameter("bookId");
		String title= request.getParameter("title");					//	タイトル
		String author= request.getParameter("author");					//	著者
		String translater= request.getParameter("translater");			//	訳者
		String publisher= request.getParameter("publisher");			//	出版社
		String publicationDate= request.getParameter("publicationDate");//	出版年月日
		String code= request.getParameter("code");						//	ISBN/ISSN
		String memo= request.getParameter("memo");						//	備考
		String keyword= request.getParameter("keyword");				//	キーワード
		String status= request.getParameter("status");					//	状態
		String detaCreator= request.getParameter("detaCreator");		//	登録者
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataCreatedDate = sdf.format(now);						//	登録日

		//	エラーメッセージ
		String errorMsg = "";
		if(title == null || title.length() == 0){errorMsg += "タイトル　";}
		if(author == null || author.length() == 0){errorMsg += "著者　";}
		//if(translater == null || translater.length() == 0){errorMsg += "訳者　";}
		if(publisher == null || publisher.length() == 0){errorMsg += "出版社　";}
		//if(publicationDate.startsWith("-") || publicationDate.contains("---"))
		//	{errorMsg += "出版年月日　";}
		//if(code == null || code.length() == 0){errorMsg += "ISBN/ISSN　";}
		//if(memo == null || memo.length() == 0){errorMsg += "備考　";}
		//if(keyword == null || keyword.length() == 0){errorMsg += "キーワード　";}
		//if(status == null || status.length() == 0){errorMsg += "状態　";}
		if(detaCreator == null || detaCreator.length() == 0){errorMsg += "登録者　";}


		//	入力が正しくない場合エラーメッセージ表示
		if(errorMsg.length() != 0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>BookSystem(ListError)</title>");
			out.println("</head>");

			out.println("<body background=\"/BookSystem/Image/backGround.png\">");
			out.println("<h1>登録</h1><hr>");
			out.println("必須項目が入力されていません。<br>");
			out.println("未入力項目【　<font color=\"red\">" + errorMsg + "</font>】<br>");
			out.println("<p><a href=\"/BookSystem/addForm.html\">"
							+ "<img src=\"/BookSystem/Image/Back.png\" alt=\"Back\" border=\"0\"></a></p>");

			out.println("<hr><p><a href=\"/BookSystem/index.html\">"
					+ "<img src=\"/BookSystem/Image/BackMenu.png\" alt=\"BackMenu\" border=\"0\"></a></p>");

			out.println("</body>");

			out.println("</html>");

		//	入力が正しい場合セット
		}else{
			book.setBookId(bookId);;
			book.setTitle(title);
			book.setAuthor(author);
			book.setTranslater(translater);
			book.setPublisher(publisher);
			book.setPublicationDate(publicationDate);
			book.setCode(code);
			book.setMemo(memo);
			book.setKeyword(keyword);
			book.setStatus(status);
			book.setDetaCreator(detaCreator);
			book.setDataCreatedDate(dataCreatedDate);

			catalog.addBook(book);

			request.setAttribute("book", book);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addJsp.jsp");
			dispatcher.forward(request, response);

		}
	}
}
