<%@ page import="model1.board.BoardDAO" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="model1.board.BoardDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.BoardPage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
  //pagingList.jsp
  BoardDAO dao=new BoardDAO();
  Map<String, Object> param=new HashMap<>();
  String searchField=request.getParameter("searchField");
  String searchWord=request.getParameter("searchWord");
  if(searchWord !=null){
    param.put("searchField", searchField);
    param.put("searchWord",searchWord);
  }

  int totalCount=dao.selectCount(param);

  // 페이징 처리 //
  int pageSize=10; //페이지당 레코드 수
  int blockSize=5; // 블록당 페이지 수
  int totalPage=(int)Math.ceil((double)totalCount/pageSize); // 전체페이지 수
  //현재 페이지
  int pageNum=1;
  String pageTemp=request.getParameter("pageNum");
  if(pageTemp != null && !pageTemp.equals("")){
    pageNum=Integer.parseInt(pageTemp);
  }

  // 목록에 출력할 게시물 범위 계산
  int start=(pageNum-1)*pageSize+1;
  int end=pageNum*pageSize;
  //int start=(pageNum-1)*pageSize;
  param.put("start",start);
  param.put("end",end);

  List<BoardDTO> boardDTOList=dao.selectPagingList(param);
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

<c:set var="searchField" value="<%=searchField%>"/>
<c:set var="searchWord" value="<%=searchWord%>"/>
<c:set var="boardList" value="<%=boardDTOList%>"/>

<form method="get">
  <table>
    <tr>
      <td align="center">
        <select name="searchField">

  <c:choose>
    <c:when test="${searchField=='content'}">
          <option value="title">제목</option>
          <option value="content" selected>내용</option>
    </c:when>
    <c:otherwise>
          <option value="title" selected>제목</option>
          <option value="content">내용</option>>내용</option>
    </c:otherwise>
  </c:choose>

        </select>
        <input type="text" name="searchWord" value="${searchWord}">
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
    <th width="10%">조회수</th>
    <th width="15%">작성일</th>
  </tr>

  <c:choose>
    <c:when test="${empty boardList}">
  <tr><td colspan="5" align="center">등록된 게시글이 없습니다</td></tr>
    </c:when>
    <c:otherwise>
      <c:forEach var="dto" items="${boardList}">
  <tr>
    <td>${dto.num}</td>
    <td><a href="view.jsp?num=${dto.num}">${dto.title}</a></td>
    <td>${dto.id}</td>
    <td>${dto.visitcount}></td>
    <td>${dto.postdate}</td>
  </tr>
      </c:forEach>
    </c:otherwise>
  </c:choose>
<%--  <%--%>
<%--      }--%>
<%--    }--%>
<%--  %>--%>
</table>
<table border="1" width="80%">
  <tr>
    <td align="center">
      <%=BoardPage.pagingStr(totalCount, pageSize, blockSize, pageNum, request.getRequestURI())%>
    </td>
    <td align="right">
      <button type="button" onclick="location.href='write.jsp'">글쓰기</button>
    </td>
  </tr>
</table>
</body>
</html>
