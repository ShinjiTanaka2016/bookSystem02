package jspdb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FileBookCatalog extends BookCatalog {
	static final String CATALOG_FILE_NAME = "c:\\jsample\books.ser";
	protected Map<String,Book> books = new HashMap<String,Book>();


	public FileBookCatalog(){
		load();
	}

	protected synchronized void load(){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(CATALOG_FILE_NAME));
			books = (Map<String,Book>)in.readObject();
			in.close();
		}catch(FileNotFoundException e){
			//	ファイルがない場合
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected synchronized void save(){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(CATALOG_FILE_NAME));
			out.writeObject(books);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Book getBook(String no) {
		return (Book) books.get(no);
	}

	@Override
	public Book[] getBooks() {
		Book resultArray[] = new Book[books.size()];
		books.values().toArray(resultArray);
		return null;
	}

	@Override
	public synchronized Book addBook(Book book) {
		String id = book.getBookId();
		if(id == null || books.containsKey(id)){
			id = createUniqueBookId();
			book.setBookId(id);
		}
		books.put(id, book);
		save();
		return book;
	}

	@Override
	public void deleteBook(String no) {
		books.remove(no);
		save();
	}

	@Override
	public Book[] searchBooks(String word) {
		ArrayList<Book> result = new ArrayList<Book>();
		Iterator<Book> i = books.values().iterator();
		while(i.hasNext()){
			Book book = (Book)i.next();
			if	(  book.getBookId().indexOf(word) != -1
				|| book.getTitle().indexOf(word) != -1
				|| book.getAuthor().indexOf(word) != -1
				|| book.getTranslator().indexOf(word) != -1
				|| book.getPublisher().indexOf(word) != -1
				|| book.getKeyword().indexOf(word) != -1
				|| book.getMemo().indexOf(word) != -1){
				   result.add(book);
			}
		}
		Book resultArray[] = new Book[result.size()];
		return resultArray;
	}

	@Override
	public void allDeleteBook() {
	}
}
