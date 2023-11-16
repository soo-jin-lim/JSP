<%@ page import="model1.board.BoardDAO" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="model1.board.BoardDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BoardDAO dao = new BoardDAO();
    Map<String, Object> param = new HashMap<>();
    String searchField = request.getParameter("searchField");
    String searchWord = request.getParameter("searchWord");
    if (searchWord != null) {
        param.put("searchField", searchField);
        param.put("searchWord", searchWord);
    }
    int totalCount = dao.selectCount(param);
    List<BoardDTO> boardDTOList = dao.selectList(param);
    dao.close();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../common/link.jsp"/>
<h2>게시글 목록보기</h2>
<h3>게시물 수 : <%=totalCount%></h3>
<form method="get">
    <table>
        <tr>
            <td align="center">
                <select name="searchField">
                    <%
                        if("content".equals(searchField)) {
                    %>
                    <option value="title">제목</option>
                    <option value="content" selected>내용</option>
                    <%
                        } else {
                    %>
                    <option value="title" selected>제목</option>
                    <option value="content">내용</option>
                    <%
                        }
                    %>
                </select>
                <input type="text" name="searchWord">
                <input type="submit" value="검색">
            </td>
        </tr>
    </table>
</form>
<table border="1" width="80%">
    <tr>
        <th width="10%">번호</th>
        <th width="50%">제목</th>
        <th width="15%">작성자</th>
        <th width="10%">작성일</th>
        <th width="15%">조회수</th>
    </tr>
    <%
        if (boardDTOList.isEmpty()) {
    %>
            <tr><td colspan = "5" align="center">등록된 게시글이 없습니다</td></tr>
    <%
        } else {
            for(BoardDTO dto:boardDTOList) {
    %>
    <tr>
        <td><%=dto.getNum()%></td>
        <td><a href="view.jsp?num=<%=dto.getNum()%>"><%=dto.getTitle()%></a></td>
        <td><%=dto.getId()%></td>
        <td><%=dto.getPostdate()%></td>
        <td><%=dto.getVisitcount()%></td>
    </tr>
    <%
            }
       }
    %>
</table>
<table border="1" width="80%">
    <tr>
        <td>
            <button type="button" onclick="location.href='write.jsp'">글쓰기</button>
        </td>
    </tr>
</table>
</body>
</html>
