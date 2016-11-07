<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jspdb.Book" %>
<%@ page import="jspdb.BookCatalog" %>
<%@ page import="jspdb.DatabaseBookCatalog" %>
<%
BookCatalog catalog = DatabaseBookCatalog.getInstance();
Book book = (Book)request.getAttribute("book");
String bookId = catalog.createUniqueBookId();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BookSystem(AddPageResult)</title>
</head>
<body background="/BookSystem/Image/backGround.png">
<h1>登録</h1><hr>

<b>登録しました</b><br><br>
図書ＩＤ　:<%= book.getBookId() %><br>
タイトル　:<%= book.getTitle() %><br>
著者　　　:<%= book.getAuthor() %><br>
訳者　　　:<%= book.getTranslator() %><br>
出版社　　:<%= book.getPublisher() %><br>
出版年月日:<%= book.getPublicationDate() %><br>
ISBN/ISSN :<%= book.getCode() %><br>
備考　　　:<%= book.getMemo() %><br>
キーワード:<%= book.getKeyword() %><br>
状態　　　:<%= book.getStatus() %><br>
登録者　　:<%= book.getDetaCreator() %><br>
登録日　　:<%= book.getDataCreatedDate() %><br><br>

<a href="/BookSystem/addForm.html">
			<img src="/BookSystem/Image/MoreAdd.png" alt="MareAdd" border="0"></a>

<hr><p><a href="/BookSystem/index.html">
			<img src="/BookSystem/Image/BackMenu.png" alt="BackMenu" border="0"></a></p>
</body>
</html>