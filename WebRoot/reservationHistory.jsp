<%--
  Created by IntelliJ IDEA.
  User: lycbel
  Date: 11/5/2017
  Time: 6:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Reservation history</title>
<style>
    .blank_row
    {
        height: 10px !important; /* overwrites any other rules */
        background-color: #FFFFFF;
    }
</style>
</head>
<%@include file="/header.jsp" %>
<body>
<table  class="fTable">
    <tr class="subHeader">
        <th>From</th><th width="20px"></th>
        <th>To</th><th width="20px"></th>
        <th>House Name</th><th width="20px"></th>
        <th>Address</th>
    </tr>
<%ReserveHistoryControl rhc = new ReserveHistoryControl();
    for(Reserve h: rhc.getUserResves(user) ){%>
    <tr onclick="location.href='house.jsp?id=<%=h.getHousId()%>';">
        <td align=center ><%=h.getStart()%></td><td> </td>
        <td align=center ><%=h.getEnd()%></td><td> </td>
        <td align=center ><%=h.getHouseName()%></td><td> </td>
        <td align=center ><%=h.getHouseAddress()%></td>
    </tr>
    <tr class="blank_row">
        <td bgcolor="#FFFFFF" colspan="3">&nbsp;</td>
    </tr>



<%}%>
</table>
</body>
</html>
