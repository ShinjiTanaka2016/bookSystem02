package jspdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseBookCatalog extends BookCatalog {
	Connection con;
	static final String DATABASE_URL = "jdbc:mysql://localhost/booksystem";
	static final String DATABASE_USER = "root";
	static final String DATABASE_PASWORD = "utsystem2016";

	//	コンストラクタ
	public DatabaseBookCatalog(){
		connect();
	}

	//	データベースがあれば接続を行う
	protected boolean connect(){
		String msg = "";
		try{
			if(con != null){
				if(con.getWarnings() == null){
					return true;
				}
				disconnect();
			}
			try{
				Class.forName("com.mysql.jdbc.Driver");
				msg = "ドライバのロードに成功しました";
			}catch(ClassNotFoundException e){
				msg = "ドライバのロードに失敗しました";
			}

			con = DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASWORD);
			if(con.getWarnings() == null){
				return true;
			}
			disconnect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}


	protected void disconnect(){
		if(con != null){
			try{
				con.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			con = null;
		}
	}

	@Override
	public Book getBook(String bookId) {
		Book book = null;
		try{
			connect();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM bookcatalog WHERE bookid = ?");
			statement.setString(1, bookId);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				book = createBook(rs);
			}
			statement.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return book;
	}

	@Override
	public Book[] getBooks() {
		Book[] bookArray = null;
		try{
			connect();

			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM bookcatalog");
			bookArray = createBooks(rs);

			statement.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return bookArray;
	}

	@Override
	public synchronized Book addBook(Book book){
		try{
			connect();
			String bookId = book.getBookId();
			if(bookId == null || bookId.length() == 0 || getBook(bookId) != null){
				bookId = createUniqueBookId();
			}
			PreparedStatement statement = con.prepareStatement
				("INSERT INTO bookcatalog VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString( 1, bookId);
			statement.setString( 2, book.getTitle());
			statement.setString( 3, book.getAuthor());
			statement.setString( 4, book.getTranslator());
			statement.setString( 5, book.getPublisher());
			statement.setString( 6, book.getPublicationDate());
			statement.setString( 7, book.getCode());
			statement.setString( 8, book.getMemo());
			statement.setString( 9, book.getKeyword());
			statement.setString(10, book.getStatus());
			statement.setString(11, book.getDetaCreator());
			statement.setString(12, book.getDataCreatedDate());
			int result = statement.executeUpdate();
			statement.close();
			return getBook(bookId);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteBook(String bookId) {
		try{
			connect();
			PreparedStatement statement = con.prepareStatement("DELETE FROM bookcatalog WHERE bookid = ?");
			statement.setString(1, bookId);
			int rs = statement.executeUpdate();
			statement.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	public void allDeleteBook() {
		try{
			connect();
			PreparedStatement statement = con.prepareStatement("DELETE FROM bookcatalog");
			int rs = statement.executeUpdate();
			statement.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	@Override
	public Book[] searchBooks(String word) {
		Book[] bookArray = null;
		try{
			connect();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM bookcatalog WHERE"
					+ " bookid LIKE ? OR title LIKE ? OR author LIKE ? OR translator LIKE ? OR"
					+ " publisher LIKE ? OR publishingdate LIKE ? OR codeid LIKE ? OR memo LIKE ? OR"
					+ " keyword LIKE ? OR statusid LIKE ? OR datacreator LIKE ?");
			String pattern = "%" + word.toUpperCase() + "%";
			for(int i = 1; i <= 11; i++){
				statement.setString(i, pattern);
			}
			ResultSet rs = statement.executeQuery();
			bookArray = createBooks(rs);
			statement.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return bookArray;
	}


	protected Book createBook(ResultSet rs) throws SQLException{
		Book book = new Book();

		book.setBookId			(rs.getString("bookid"));
		book.setTitle			(rs.getString("title"));
		book.setAuthor			(rs.getString("author"));
		book.setTranslater		(rs.getString("translator"));
		book.setPublicationDate	(rs.getString("publishingdate"));
		book.setPublisher		(rs.getString("publisher"));
		book.setCode			(rs.getString("codeid"));
		book.setStatus			(rs.getString("statusid"));
		book.setKeyword			(rs.getString("keyword"));
		book.setMemo			(rs.getString("memo"));
		book.setDataCreatedDate	(rs.getTimestamp("datacreateddate").toString().substring(0, 16));
		book.setDetaCreator		(rs.getString("datacreator"));

		return book;
	}


	protected Book[] createBooks(ResultSet rs) throws SQLException{
		ArrayList<Book> books = new ArrayList<Book>();

		while(rs.next()){
			books.add(createBook(rs));
		}

		Book[] bookArray = new Book[books.size()];
		books.toArray(bookArray);
		return bookArray;
	}
}
