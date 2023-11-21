<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-14
  Time: 오후 2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="./isLoggedIn.jsp"%> <!--로그인 확인-->
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function validateForm(form) {  // 폼 내용 검증
            if (form.title.value == "") {
                alert("제목을 입력하세요.");
                form.title.focus();
                return false;
            }
            if (form.content.value == "") {
                alert("내용을 입력하세요.");
                form.content.focus();
                return false;
            }
        }
    </script>
</head>
<body>
<jsp:include page="../common/link.jsp"/>
<h2>게시글 등록 폼</h2>
<form name="writeFrm" method="post" action="pagingWriteProcess.jsp"
      onsubmit="return validateForm(this)">
    <table>
        <tr>
            <td>제목</td>
            <td><input type="text" name="title" style="width: 90%"></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><textarea name="content" style="width: 90%;height: 100px"></textarea></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">게시글 등록</button>
                <button type="reset">새로고침</button>
                <button type="button" onclick="location.href='list.jsp'">목록보기</button>
            </td>
        </tr>
    </table>

</form>
</body>
</html>
