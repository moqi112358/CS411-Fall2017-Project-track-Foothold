<%--
  Created by IntelliJ IDEA.
  User: lycbel
  Date: 12/1/2017
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/header.jsp" %>

<head>
    <link type="text/css" rel="stylesheet" href="/css/profile.css" />
    <style>
        body{text-align:center}
    </style>
    <script type="text/javascript">
        //auto expand textarea
        function adjust_textarea(h) {
            h.style.height = "20px";
            h.style.height = (h.scrollHeight)+"px";
        }
    </script>
    <title>Be Owner</title>
</head>
<body>

<form method="post" enctype="multipart/form-data"  accept-charset="utf-8" action="/usr/UserProfileServlet"  class="form-style-7">

    <li>
        <label for="fname">firstName:</label>
        <input id="fname" name="firstName" type="text" required value="<%=user.getFirstName()%>">
        <span>Enter your first name here</span>
    </li>
    <li>
        <label for="lname">lastName:</label>
        <input id="lname" name="lastName" type="text" required value="<%=user.getLastName()%>">
        <span>Enter your last name here</span>
    </li>
    <li>
        <label for="pname">Phone:</label>
        <input id="pname" name="ph" type="text" required value="">
        <span>Enter your phone here</span>
    </li>

    <li>
        <label for="loname">Location:</label>
        <input id="loname"  name="lo" type="text" required value="">
        <span>Enter your location here</span>
    </li>


    <li>
        <label for="loname">Description:</label>
        <textarea rows="10" cols="55" name="description"> </textarea>
        <span>Enter your description here</span>
    </li>

    <input hidden="hidden" value="change" name="change">
</br>
    <li>
        <label for="loname"> choose a picture of you:</label>
        <input type="file" name="file" />
    </li>
    <input type="submit" value="Submit">
</form>

</body>
</html>
