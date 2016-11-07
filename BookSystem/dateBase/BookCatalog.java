package jspdb;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BookCatalog {

	public abstract Book getBook(String no);
	public abstract Book[] getBooks();
	public abstract Book addBook(Book book);
	public abstract void deleteBook(String bookid);
	public abstract void allDeleteBook();
	public abstract Book[] searchBooks(String word);

	public String createUniqueBookId(){
		Date now = new Date();
		String id;
		id = new SimpleDateFormat("yyyyMMddHH").format(now);
		while(getBook(id) != null){
			int intid = Integer.parseInt(id);
			id = Integer.toString(intid + 1);
		}
		return id;
	}

	static BookCatalog sharedInstance;
	static public BookCatalog getInstance(){
		if(sharedInstance == null){
			sharedInstance = new DatabaseBookCatalog();
		}
		return sharedInstance;
	}
}
