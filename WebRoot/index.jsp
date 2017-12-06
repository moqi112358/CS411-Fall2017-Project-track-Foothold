<%--
  Created by IntelliJ IDEA.
  User: lycbel
  Date: 11/3/2017
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/header.jsp" %>
<link rel="stylesheet" type="text/css" href="/css/search.css"/>
<body>
<br>&nbsp;</br>
<br>&nbsp;</br>

<% String message = (String)request.getAttribute("messageup");if(message!=null){%>
<h2><%=message%></h2>
<%}%>
</br>
<img src = "/img/logo.png" align="middle" height=100 width=600>

    <form action="/search.jsp?index=0" method="post" class="form-wrapper">
        <input type="text" id="search" name="searchContent" placeholder="Search for address...." required>
        <input type="submit" value="go" id="submit">
    </form>

</body>
</html>
