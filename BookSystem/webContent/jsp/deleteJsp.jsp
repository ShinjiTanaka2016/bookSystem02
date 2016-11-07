<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body background="/BookSystem/Image/backGround.png">
<h1>削除</h1><hr>

<%= request.getParameter("deleteId") %>を削除しました。<br><hr>
<p>
<a href="/BookSystem/index.html"><img src="/BookSystem/Image/BackMenu.png" alt="BackMenu" border="0"></a>
</p>
</body>
</html>