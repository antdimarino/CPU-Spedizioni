<%--
  Created by IntelliJ IDEA.
  User: tony
  Date: 17/12/2019
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<% session.setAttribute("username",null);

    session.invalidate();
    response.sendRedirect("login.jsp");
%>

</body>
</html>
