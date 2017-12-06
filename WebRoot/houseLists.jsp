<%@ page import="modules.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="controls.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Andy
  Date: 11/6/2017
  Time: 7:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/header.jsp" %>


<%

    List<HouseBean> hbs = new HouseControl().getHouseList(user);
    for(HouseBean hb : hbs){
%>
  <a href="/uploadHouse.jsp?edit=true&id=<%=hb.getHouseId() %>" >
        <%="click to modify " + hb.getName()%>
  </a>
  </br>
</br>

<%}%>


