<%--
  Created by IntelliJ IDEA.
  User: gsj
  Date: 2017/12/5
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="modules.*" %>
<%@ page import="controls.*" %>
<%@ page import="exception.MyLog" %>
<html dir="ltr" lang="en-US">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="all" href="css/daterangepicker.css">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/moment.js"></script>
    <script type="text/javascript" src="js/daterangepicker.js"></script>

</head>
<%@include file="/header.jsp" %>

    <%
    long oid = 0;
    UserControl uerControl = new UserControl();
    try {
        oid = Long.parseLong(request.getParameter("id"));
    }catch (Exception e){
       response.sendRedirect("/index.jsp");
    }
    Owner owner = uerControl.getOwnerInfo(oid);
%>
<title>Owner Information</title>

<body>
<h2> Owner Information</h2>

</br>
<p><img width="300px" heigth="340px"  src="<%=owner.getHost_picture_url()%>"></p>
<p>Owner ID: <%=oid%></p>
<p>Name: <%=owner.getFirstName()%> </p>
<p>Phone: <%=owner.getPhone()%></p>
<p>Email: <%=owner.getUserEmail()%></p>
<p>Host Location: <%=owner.getHost_location()%></p>
<p>Description:  <%=owner.getDescription()%></p>
</br></br>

 <a href="#" onClick="javascript :history.back(-1);">Back</a>
</body>
</html>
