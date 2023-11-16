<%@ page import="model1.member.MemberDAO" %>
<%@ page import="model1.member.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userId=request.getParameter("user_id");
    String userPw=request.getParameter("user_pw");

    MemberDAO dao = new MemberDAO();
    model1.member.MemberDTO memberDTO = dao.getMemberDTO(userId, userPw);
    dao.close();
    if(memberDTO.getId()!=null){
        session.setAttribute("userId", memberDTO.getId());
        session.setAttribute("userName", memberDTO.getName());
        response.sendRedirect("../board/pagingList.jsp");
    }
    else {
        request.setAttribute("loginErrMsg", "로그인 오류입니다.");
        request.getRequestDispatcher("loginForm.jsp").forward(request,response);
    }
%>
<html>
<head>
    <title>로그인 오류</title>
</head>
<body>

</body>
</html>
