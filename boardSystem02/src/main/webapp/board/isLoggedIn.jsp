<%@ page import="utils.JSFunction" %><%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-14
  Time: 오후 2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session.getAttribute("userId")==null){
        JSFunction.alertLocation("로그인 후 이용해 주세요",
                "../member/loginForm.jsp",out);
        return;
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
