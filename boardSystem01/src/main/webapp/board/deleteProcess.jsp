<%@ page import="model1.board.BoardDAO" %>
<%@ page import="utils.JSFunction" %><%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-14
  Time: 오후 4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int num=Integer.parseInt(request.getParameter("num"));
    BoardDAO dao=new BoardDAO();
    int iResult=dao.deletePost(num);
    if(iResult==1){
        JSFunction.alertLocation("삭제 성공","list.jsp",out);
    }else{
        JSFunction.alertBack("삭제 실패", out);
    }

%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
