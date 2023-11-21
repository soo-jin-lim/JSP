<%@ page import="model1.board.BoardDTO" %>
<%@ page import="model1.board.BoardDAO" %><%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-15
  Time: 오후 2:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="./isLoggedIn.jsp"%>

<%
    int num=Integer.parseInt(request.getParameter("num"));
    String title=request.getParameter("title");
    String content=request.getParameter("content");
    BoardDTO dto=new BoardDTO();
    dto.setNum(num);
    dto.setTitle(title);
    dto.setContent(content);

    BoardDAO dao=new BoardDAO();
    int iResult=dao.updateEdit(dto);
    dao.close();

    if(iResult==1){
        response.sendRedirect("view.jsp?num="+num);
    }else{
        JSFunction.alertBack("수정하기 실패", out);
    }
%>
