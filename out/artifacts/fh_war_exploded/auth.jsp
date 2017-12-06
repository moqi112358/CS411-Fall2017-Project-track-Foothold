<%--
  Created by IntelliJ IDEA.
  User: lycbel
  Date: 11/3/2017
  Time: 12:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modules.User" %>
<%

    boolean logged = false;
    User user =null;

    if(session.getAttribute("user")!=null) {
        user  = (User) session.getAttribute("user");
        logged = true;
    }
    if(session.getAttribute("user")==null){
        response.sendRedirect("/loginAndSignup.jsp");
    }


%>
