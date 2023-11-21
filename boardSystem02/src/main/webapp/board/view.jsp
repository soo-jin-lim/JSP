<%@ page import="model1.board.BoardDAO" %>
<%@ page import="model1.board.BoardDTO" %><%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-14
  Time: 오후 3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    int num=Integer.parseInt(request.getParameter("num"));
    BoardDAO dao=new BoardDAO();
    dao.updateVisitCount(num);
    BoardDTO dto=dao.selectView(num);
    dao.close();
%>
<html>
<head>
    <title>Title</title>
    <script>
        function deletePost() {
            var confirmed=confirm("정말 삭제하시겠습니까?")
            if(confirmed){
                var form=document.viewFrm;
                form.method="post";
                form.action="deleteProcess.jsp";
                form.submit();
            }

        }
    </script>
</head>
<body>
<jsp:include page="../common/link.jsp"/>
<h2>회원제 게시판 상세보기</h2>
<c:set var="dto" value="<%=dto%>"/>
<form name="viewFrm">
    <input type="hidden" name="num" value="<%=num%>">
    <table>
        <tr>
            <td>번호</td>
            <td><%=num%></td>
        </tr>
        <tr>
            <td>제목</td>
            <td><%=dto.getTitle()%></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><%=dto.getContent()%></td>
        </tr>
        <tr>
            <td>작성자</td>
            <td><%=dto.getId()%></td>
        </tr>
        <tr>
            <td>작성일</td>
            <td><%=dto.getPostdate()%></td>
        </tr>
        <tr>
            <td>조회수</td>
            <td><%=dto.getVisitcount()%></td>
        </tr>
        <tr>
            <td colspan="2">
<%--                <% if(session.getAttribute("userId") !=null &&--%>
<%--                        session.getAttribute("userId").toString().equals(dto.getId())){ %>--%>
                <c:if test="${not empty userId && userId==dto.id}">
                <button type="button" onclick="location.href='edit.jsp?num=<%=num%>'">수정하기</button>
                <button type="button" onclick="deletePost();">삭제하기</button>
                </c:if>
<%--                <% } %>--%>
                <button type="button" onclick="location.href='list.jsp'">목록보기</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
