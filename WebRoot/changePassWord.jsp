<%@ page import="DB.AuthDB" %>
<%@ page import="controls.AuthControl" %><%--
  Created by IntelliJ IDEA.
  User: lycbel
  Date: 11/5/2017
  Time: 12:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/header.jsp" %>
<link rel="stylesheet" href="/css/profile.css">
<%
    int type = 0; // means nothing to change.
  if(request.getParameter("submitted")!=null){
      User u = (User)session.getAttribute("user");
      AuthControl ac = new AuthControl();
      type= ac.changePassWord(u,request.getParameter("newPass"),request.getParameter("confirmPass"));

  }
  if(type==1){%>
<p>password successfully changed<p>
<%
    }else if(type == -1){
  %>
  <p>password not match<p>
<%
    }else  if(type == -2){%>
<p>need to fill all field<p>
   <% }
  if(type!=1){
%>

<form action="/changePassWord.jsp" method="post"   class="form-style-7">

    <li>
        <label for="fname"> New Password:</label>
        <input id="fname" type=password maxlength=20 name="newPass" required>

        <span>Enter password</span>
    </li>
    <li>
        <label for="lname">Confirm:</label>
        <input id="lname" type=password maxlength=20 required name="confirmPass">
        <span>Enter your last name here</span>
    </li>

    <input type="submit" value="Change Password">
    <input value = "1" name = "submitted" hidden="hidden">
</form>

<%}%>
