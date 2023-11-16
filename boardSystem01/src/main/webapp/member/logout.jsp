<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //방법1 : session 속성 name을 제거하는 방법
    session.removeAttribute("userId");
    session.removeAttribute("userName");

    //방법2 : 모든 속성 한번에 삭제
    session.invalidate();

    response.sendRedirect("loginForm.jsp");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
