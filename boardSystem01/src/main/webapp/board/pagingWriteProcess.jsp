<%@ page import="model1.board.BoardDAO" %>
<%@ page import="model1.board.BoardDTO" %>
<%@ page import="utils.JSFunction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String title = request.getParameter("title");
    String content = request.getParameter("content");


    BoardDTO dto =new BoardDTO();
    dto.setId(session.getAttribute("userId").toString());
    dto.setTitle(title);
    dto.setContent(content);

    BoardDAO dao = new BoardDAO();
    //int iResult = dao.insertWrite(dto);

    int iResult=0;
    for(int i=0; i<=100; i++){
        dto.setTitle(title+"_"+i);
        iResult=dao.insertWrite(dto);
        //데이터가 100개 들어가는지.
    }
    dao.close();

    if (iResult == 1) {
        response.sendRedirect("list.jsp");
    }else {
        JSFunction.alertBack("글쓰기 실패하였습니다.", out);
    }
%>

