<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<h1 align="center">Beer Recommendations JSP</h1>
<p>

<jsp:useBean id="styles" scope="request" type="java.util.List"/>
<c:forEach var="s" items="${styles}">
    <br>try: ${s}
</c:forEach>
</p>
</body>
</html>
