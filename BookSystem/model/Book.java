package model;

import java.io.Serializable;

public class Book implements Serializable{

	private String bookId;				//	図書ＩＤ
	private String title;				//	タイトル
	private String author;				//	著者
	private String translater;			//	訳者
	private String publisher;			//	出版社
	private String publicationDate;		//	出版年月日
	private String code;				//	ISBN/ISSN
	private String memo;				//	備考
	private String keyword;				//	キーワード
	private String status;				//	状態
	private String detaCreator;			//	登録者
	private String dataCreatedDate;		//	登録日


	public String getBookId() {return bookId;}
	public String getTitle() {return title;}
	public String getAuthor() {return author;}
	public String getTranslater() {return translater;}
	public String getPublisher() {return publisher;}
	public String getPublicationDate() {return publicationDate;}
	public String getCode() {return code;}
	public String getStatus() {return status;}
	public String getKeyword() {return keyword;}
	public String getMemo() {return memo;}
	public String getDetaCreator() {return detaCreator;}
	public String getDataCreatedDate() {return dataCreatedDate;}


	public void setBookId(String bookId) {this.bookId = bookId;}
	public void setTitle(String title) {this.title = title;}
	public void setAuthor(String author) {this.author = author;}
	public void setTranslater(String translater) {this.translater = translater;}
	public void setPublisher(String publisher) {this.publisher = publisher;}
	public void setPublicationDate(String publicationDate) {this.publicationDate = publicationDate;}
	public void setCode(String code) {this.code = code;}
	public void setStatus(String status) {this.status = status;}
	public void setKeyword(String keyword) {this.keyword = keyword;}
	public void setMemo(String memo) {this.memo = memo;}
	public void setDetaCreator(String detaCreator) {this.detaCreator = detaCreator;}
	public void setDataCreatedDate(String dataCreatedDate) {this.dataCreatedDate = dataCreatedDate;}
}
