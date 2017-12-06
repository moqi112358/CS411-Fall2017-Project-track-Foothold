<%--
  Created by IntelliJ IDEA.
  User: lycbel
  Date: 11/5/2017
  Time: 3:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<html>
<%@page import="modules.*" %>
<%@page import="controls.*" %>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@include file="/header.jsp" %>


<body>
<%
    List<House> resultList = null;
    SearchControl  control = new SearchControl();
    String searchContent = "";
    String tags;
    int curindex = 0;
    long recordCount = -1;

    int index = Integer.valueOf(request.getParameter("index"));
    searchContent = request.getParameter("searchContent");
    tags = request.getParameter("tags");
    System.out.println(tags);
    if(searchContent == null){
        SearchIndex searchIndex = (SearchIndex)session.getAttribute("index");
        searchContent = searchIndex.getSearchContent();
    }else{
        session.setAttribute("index", new SearchIndex(index, searchContent));
    }

    if(tags != null){
        resultList = control.getSearchResultWithTags(searchContent, index, tags);
        recordCount = control.getSearchResultCountWithTags(searchContent, tags);
        System.out.println(tags);
        //define tag filter operations
    }else {
        tags = "";
        resultList = control.getSearchResult(searchContent, index);
        recordCount = control.getSearchResultCount(searchContent);
    }

%>
<link rel="stylesheet" type="text/css" href="/css/search.css"/>
<link rel="stylesheet" type="text/css" href="/css/search1.css"/>
<link rel="stylesheet" type="text/css" href="/css/search2.css"/>

<form action="/search.jsp?index=0" method="post" class="form-wrapper">
    <input type="text" id="search" name="searchContent" value="<%=searchContent%>" placeholder="Search for..." required>
    <input type="submit" value="go" id="submit">
</form>
<%
    if (resultList != null) {
        if (resultList.size() == 0) {
%>
<span class="Error">No results found.  Please change your search criteria and search again.</span>
<%
} else {
%>
<form align="right" action="/search.jsp?index=0"  method="post" class="form-wrapper1">
    <input id="search1" type="text" name="tags"
           value="<%=tags%>" >
    <input type="submit" value="Search Tags" id="submit1">
</form>

<form align="center" class="form-wrapper2">
    <table class="fTable">
        <tr class="subHeader">
            <th>Picture</th>
            <th>House ID</th>
            <th>Listing Name</th>
            <th>Address</th>
        </tr>
        <%
            //List<PersonnelBean> personnelList = new ArrayList<PersonnelBean>();
            for (House h: resultList) {
        %>
        <tr onclick="location.href='house.jsp?id=<%=h.getHouseId()%>';">
            <td align=center ><img width="218px" height="146px" src=<%=h.getHousePicLink()%> alt="pic"></td>
            <td align=center ><%=h.getHouseId()%></td>
            <td align=center ><%=h.getName()%></td>
            <td align=center ><%=h.getAddress()%></td>
        </tr>
        <tr>
            <td>  </td>
        </tr>
        <%
                }
            }
        %>
    </table>

    <%
        int pageNum = (int)Math.ceil((double)recordCount/30.0);
        for(int i=1;i<pageNum+1;i++){
    %>
    <a href='/search.jsp?index=<%=curindex%>'> <%=i%></a>
    <%
                curindex += 30;
            }
        }%>
</form>

</body>
</html>
