<%@ page import="model1.board.BoardDTO" %>
<%@ page import="model1.board.BoardDAO" %>
<%@ page import="utils.JSFunction" %><%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-14
  Time: 오후 2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String title=request.getParameter("title");
  String content=request.getParameter("content");

  BoardDTO dto=new BoardDTO();
  dto.setId(session.getAttribute("userId").toString());
  dto.setTitle(title);
  dto.setContent(content);


  BoardDAO dao=new BoardDAO();
  //int iResult=dao.insertWrite(dto);

  int iResult=0;
  for(int i=0; i<=100; i++){
    dto.setTitle(title+"_"+i);
    iResult=dao.insertWrite(dto);
  }
  dao.close();

  if(iResult==1){
    response.sendRedirect("list.jsp");
  }else{
    JSFunction.alertBack("글쓰기 실패하였습니다", out);
  }
%>
