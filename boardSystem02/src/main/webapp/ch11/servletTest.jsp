<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-21
  Time: 오후 2:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>${msg}</h2>
<form action="/servlet/test.do" method="post">
  <button type="submit">요청</button>
</form>

<a href="/servlet/test.do">요청</a>
</body>
</html>
