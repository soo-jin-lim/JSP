<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-11-20
  Time: 오후 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="num1" value="123456"/>
콤마 O: <fmt:formatNumber value="${num1}"/><br>
콤마 1: <fmt:formatNumber value="${num1}" groupingUsed="false"/><br>
<fmt:formatNumber value="${num1}" type="currency" var="printNum1"/>
통화기호 : ${printNum1}<br>
<fmt:formatNumber value="0.3" type="percent" var="printNum2"/>
퍼센트 : ${printNum2}<br>

<fmt:parseNumber value="12,345.04" pattern="0,000.00" var="printNum3"/>
문자열을 숫자로 : ${printNum3}<br>

<c:set var="today" value="<%=new java.util.Date()%>"/>
${today}

<h2>날짜 포맷</h2>
full :<fmt:formatDate value="${today}" type="date" dateStyle="full"/><br/>
short : <fmt:formatDate value="${today}" type="date" dateStyle="short"/><br/>
long : <fmt:formatDate value="${today}" type="date" dateStyle="long"/><br/>
default : <fmt:formatDate value="${today}" type="date" dateStyle="default"/><br/>

<h2>시간 포맷</h2>
full :<fmt:formatDate value="${today}" type="time" dateStyle="full"/><br/>
short : <fmt:formatDate value="${today}" type="time" dateStyle="short"/><br/>
long : <fmt:formatDate value="${today}" type="time" dateStyle="long"/><br/>
default : <fmt:formatDate value="${today}" type="time" dateStyle="default"/><br/>

<h2>날짜 시간 포맷</h2>
full :<fmt:formatDate value="${today}" type="both" dateStyle="full"/><br/>
short : <fmt:formatDate value="${today}" type="both" dateStyle="short"/><br/>
long : <fmt:formatDate value="${today}" type="both" dateStyle="long"/><br/>
default : <fmt:formatDate value="${today}" type="both" dateStyle="default"/><br/>

<h2>pattern 사용</h2>
pattern :<fmt:formatDate value="${today}" type="both" pattern="yyyy-MM-dd hh:mm:ss"/><br/>

<h2>로케일 설정</h2>
한글로 설정 : <fmt:setLocale value="ko_kr"/>
<fmt:formatNumber value="10000" type="currency"/>
<fmt:formatDate value="${today}" /><br/>

 일어로 설정 : <fmt:setLocale value="ja_JP"/>
<fmt:formatNumber value="10000" type="currency"/>
<fmt:formatDate value="${today}" /><br/>
영어로 설정 : <fmt:setLocale value="en_US"/>
<fmt:formatNumber value="10000" type="currency"/>
<fmt:formatDate value="${today}" /><br/>
</body>
</html>
