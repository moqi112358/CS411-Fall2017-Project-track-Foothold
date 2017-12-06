<%--
  Created by IntelliJ IDEA.
  User: lycbel
  Date: 11/5/2017
  Time: 5:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="modules.*" %>
<%@ page import="controls.*" %>
<%@ page import="exception.MyLog" %>
<html dir="ltr" lang="en-US">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/housePage.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="all" href="css/daterangepicker.css">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/moment.js"></script>
    <script type="text/javascript" src="js/daterangepicker.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/houseform.css" />

</head>
<%@include file="/header.jsp" %>

<%
    long houseId = 0;
    UserControl uerControl = new UserControl();
    try {
        houseId = Long.parseLong(request.getParameter("id"));
    }catch (Exception e){
       response.sendRedirect("/index.jsp");
    }
    HouseControl hc = new HouseControl();
    HouseBean hb = hc.getHouseBean(houseId);
    if(request.getParameter("fill")!=null){
        hc.storeReviewByid(Review.forStore(request.getParameter("review"),user.getUserID(),houseId));
    }
    Long oid = hb.getOwnerID();
    Owner owner = uerControl.getOwnerInfo(oid);
%>
<title>House Information</title>

<%
ReservationControl rhc = new ReservationControl();
String date = request.getParameter("daterange");
boolean suc = false;
boolean fill = request.getParameter("onfill")!=null;
if(fill){
   suc =rhc.reserve(houseId,user.getUserID(),date);
}

%>
<body>
<%if(suc){%>
<h2> Successfully Reserved!</h2>
<%}else{%>
<%if(fill){%>
Reservation Fault!
<%}%>
<div class="center">
    <b>House Name:</b>
    <label><%=hb.getName()%></label>

<div class="smallPart">
    <b>street: </b> <span><%=hb.getStreet()%> </span></br></br>

    <b>city: </b> <span><%=hb.getCity()%></span> &nbsp; &nbsp;

    <b> state: </b> <span><%=hb.getState()%></span> &nbsp; &nbsp;

    <b> zipcode: </b> <span><%=hb.getZipcode()%></span></br></br>

    <b>property_type: </b> <span><%=hb.getProperty_type()%></span>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;

    <b> room_type: </b> <span><%=hb.getRoom_type()%></span> </br> </br>

    <b> bathrooms: </b><span><%=hb.getBathrooms()%></span>&nbsp; &nbsp;&nbsp;

    <b> bedrooms: </b> <span><%=hb.getBedrooms()%></span>&nbsp; &nbsp;&nbsp;

    <b> beds: </b><span><%=hb.getBeds()%></span>;&nbsp; &nbsp;&nbsp;


    <b> guests_includes: </b><span><%=hb.getGuests_includes()%></span>

    </br></br>

    <b> price: </b><span><%=hb.getPrice()%></span>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;

    <b> security_deposites: </b><span><%=hb.getSecurity_deposites()%></span>

</div>
    </br></br>

    <%if(hb.getThumbnail_url()!=null&&!hb.getThumbnail_url().equals("")){%>
    <img src="<%=hb.getThumbnail_url()%>"> </br></br>
    <%}%>

<%for(String s : hb.getUrls()){%>
    <img src="<%=s%>"> </br></br>
<%}%>



<b>Space:</b>
<p><%=hb.getSpace()%></p>
<b>description:</b>
<p><%=hb.getDescription()%></p>
<b>notes: </b>
<p><%=hb.getNotes()%></p>
<b>transit: </b>
<p><%=hb.getTransit()%></p>
<b>access: </b>
<p><%=hb.getAccess()%></p>
<b>interaction: </b>
<p><%=hb.getInteraction()%></p>
<b>house_rules: </b>
<p><%=hb.getHouse_rules()%></p>
    <a href="owner.jsp?id=<%=oid%>"><b>Owner: <%=owner.getFirstName()%> </b></a>



</br></br>


</div>

<div class="center">
<form onsubmit="return checkV()" class="form-style-7">
<input hidden="hidden" value="submit" name="onfill">
<input hidden="hidden" value="<%=houseId%>" name="id">
<%@include file="/datePicker.jsp" %>
<button class="button2" > Reserve now! </button>
</form>
<%}%>

<form class="form-style-7">
    <label for="loname">Review:</label>
    <li>

<textarea rows="7" cols="100" name="review" id="loname">
</textarea>
    </li>
    <input hidden="hidden" value="filled" name="fill">
    <input hidden="hidden" value="<%=houseId%>" name="id">
    <input type="submit" value="submit review">
</form>
    <a href="#" onClick="javascript :history.back(-1);">Back</a>
<%for(Review rv : hc.getReviewByhouseId(houseId)){%>
<p><%=rv.getReview()%></p>
<p  style="font-weight:bold">Written by: <%=rv.getReviwerName()%></p>
<%}%>
</div>
</body>
</html>
