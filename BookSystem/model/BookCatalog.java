package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BookCatalog {

	static final String CATALOG_FILE_NAME = "C:/jsample/BookCatalog.sar";
	protected Map<String,Book> books = new HashMap<String,Book>();

	private static final BookCatalog instance = new BookCatalog();

	private BookCatalog(){
		load();
	}

	protected void load(){
		try{
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream(CATALOG_FILE_NAME));
			books = (Map<String,Book>)in.readObject();
			in.close();
		}catch(FileNotFoundException e){
			System.out.println("NoFile");
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	protected synchronized void save(){

		try{
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(CATALOG_FILE_NAME));
			out.writeObject(books);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Book getBook(String bookId){
		return (Book)books.get(bookId);
	}

	public Book[] getBooks(){
		Book resultArray[] = new Book[books.size()];
		books.values().toArray(resultArray);
		return resultArray;
	}

	public void deleteFile(){

		File f = new File("CATALOG_FILE_NAME");
		f.delete();
		books.clear();

	}
/*
	protected String createUniqueBookId(){
		Date now = new Date();
		String id;
		id = new SimpleDateFormat("yyyyMMdd").format(now);
		while (getBook(id) != null){
			int intId = Integer.parseInt(id);
			id = Integer.toString(intId + 1);
		}
		return id;
	}
*/

	public String createUniqueBookId(){
		String id = "00000001";

		while (getBook(id) != null){
			int intId = Integer.parseInt(id);
			id = String.format("%08d", intId + 1);
		}

		return id;
	}


	public synchronized Book addBook(Book book){
		String id = book.getBookId();
		if(id == null || books.containsKey(id)){
			id = createUniqueBookId();
			book.setBookId(id);
		}
		books.put(id,book);
		save();
		return book;
	}

	public void deleteBook(String bookId){
		books.remove(bookId);
		save();
	}


	public Book[] searchBooks(String word){
		ArrayList<Book> result = new ArrayList<Book>();
		Iterator<Book> i = books.values().iterator();

		while(i.hasNext()){
			Book book = (Book)i.next();
			if(	book.getBookId().indexOf(word) != -1
			||	book.getTitle().indexOf(word) != -1
			||	book.getAuthor().indexOf(word) != -1
			||	book.getTranslater().indexOf(word) != -1
			||	book.getPublisher().indexOf(word) != -1
			||	book.getKeyword().indexOf(word) != -1
			||	book.getMemo().indexOf(word) != -1){
				result.add(book);
			}
		}
		Book resultArray[] = new Book[result.size()];
		result.toArray(resultArray);
		if(resultArray.length == 0){
			return null;
		}else{
			return resultArray;
		}
	}

	public static BookCatalog getInstance(){
		return instance;
	}
}
