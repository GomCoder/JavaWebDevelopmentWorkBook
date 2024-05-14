<%--
  Created by IntelliJ IDEA.
  User: devkdwDesktop
  Date: 2024-03-01 0001
  Time: 오후 5:57
  Todo List 목록 페이지
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>List Page</h1>
    <ul>
        <c:forEach var="dto" items="${list}">
         <li>${dto}</li>
        </c:forEach>
    </ul>
</body>
</html>
