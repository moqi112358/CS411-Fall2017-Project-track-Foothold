<%--
  Created by IntelliJ IDEA.
  User: lycbel
  Date: 12/3/2017
  Time: 5:31 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="modules.*" %>
<%@ page import="controls.*" %>
<%@ page import="exception.MyLog" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Enumeration" %>
<%@include file="/auth.jsp" %>

<%
    HouseBean hb;

    HashMap<String,String> requ = new HashMap<>();

    Enumeration paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
        String paramName = (String) paramNames.nextElement();
        String[] paramValues = request.getParameterValues(paramName);
        if (paramValues.length == 1) {
            String paramValue = paramValues[0];
            if (paramValue.length() != 0) {
                requ.put(paramName, paramValue);
            }
        }
    }
    hb = new HouseBean(requ,null,null,null,user);

%>
<%=new HouseBasic().getEstimatedPrice((hb))%>
