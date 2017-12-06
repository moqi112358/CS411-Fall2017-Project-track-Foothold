<%--
  Created by IntelliJ IDEA.
  User: lycbel
  Date: 11/3/2017
  Time: 12:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modules.User" %>
<%@ page import="modules.Owner" %>
<head>
    <link rel="stylesheet" type="text/css" href="/css/header.css"/>
</head>
<%@include file="/auth.jsp" %>
<div class="topnav" >
    <a class="active" href="/index.jsp">Home</a>
    <%if(!logged){%>
    <a href="/loginAndSignup.jsp">Login/Signup</a>
    <%}else{%>
    Welcome! <%=user.getFirstName()+" "%>
    <a href="/userProfile.jsp">View/Edit profile</a>
    <a href="/reservationHistory.jsp">View reservations</a>

    <a href="/changePassWord.jsp">Change Password</a>
    <%if(user.isOwner()){%>
    <a href="/uploadHouse.jsp">Upload a House</a>
    <a href="/houseLists.jsp">ViewHouses</a>
    <%} else{%>
    <a href="/beOwner.jsp">To be Owner</a>
    <%}%>
    <a href="/logout.jsp">Logout</a>
    <%}%>
</div>

<div style="text-align:  right; margin-right: 20px">
    <h2>Welcome!<%if(logged){%><%="  "+user.getFirstName()%>  <%}%></h2>

</div>


