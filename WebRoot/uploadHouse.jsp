<%--
  Created by IntelliJ IDEA.
  User: lycbel
  Date: 11/5/2017
  Time: 5:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="modules.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="controls.*" %>
<html class="supernova">
<%@include file="/header.jsp" %>
<%
    boolean editMode = false;
    boolean errorMode = false;
     HouseBean hb = null;
    if(request.getAttribute("messageup")!=null){
        errorMode = true;
    }
    if(request.getParameter("edit")!=null||request.getAttribute("edit")!=null){
      editMode = true;

}
  if(errorMode&&editMode){
        hb = (HouseBean) session.getAttribute("hb");
  }
  if(!errorMode&&editMode){
        hb = new HouseControl().getHouseBean(Long.parseLong(request.getParameter("id")));
      session.setAttribute("hb",hb);
  }
    if(errorMode&&!editMode){
        hb =      (HouseBean) request.getAttribute("hb");
    }
  if(!errorMode&&!editMode){
     hb = null;
  }


%>
<head>

    <link rel="shortcut icon" href="https://cdn.jotfor.ms/favicon.ico">

    <title>House Form</title>
    <link href="/css/form_cs1.css" rel="stylesheet" type="text/css" />
    <link type="text/css" rel="stylesheet" href="/css/form_cs2.css" />
    <link type="text/css"  rel="stylesheet" href="/css/form_cs3.css" />
    <link type="text/css" rel="stylesheet" href="/css/form_cs4.css"/>
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>



<body>
<%if(request.getAttribute("messageup")!=null){%>
<%=request.getAttribute("messageup")%>
<%}%>

<h1>upload house</h1>
<form   action="/picture/UploadServlet" enctype="multipart/form-data" class="jotform-form"  method="post" name="form_73095449184162" id="houseForm" accept-charset="utf-8">
    <%if(editMode){%>
<input hidden="hidden" value="edit" name="edit">
    <%}%>
    <%@include file="/houseForm.jsp" %>


    <%if(!editMode){%>
    <div id="upload_form">
            <li class="form-line" data-type="control_textarea" >
            <div >
                choose a summary picture:<input type="file" name="thum" />
                <br/>
                <br/>
                <label>Choose detailed pictures</label>
            </div>
            </li  >
    </div>
    <%}else{%>
                choose a new summary picture:<input type="file" name="thum" />
            <div id="upload_form">


                <li class="form-line jf-required" data-type="control_checkbox" id="id_15">
                    <label class="form-label form-label-top form-label-auto">
                        which detailed picture do you want to delete?
                    </label>
<%for(int i = 0; i<hb.getNames().size();i++){%>
                            <div class="form-input-wide">
                                <div class="form-single-column" data-component="checkbox">
                                    <span class="form-checkbox-item" style="clear:left;">
                                              <span class="dragger-item">
                                              </span>
                                              <input type="checkbox" class="form-checkbo" name="deletePics" value="<%=hb.getUrls().get(i)%>"/>
                                              <label> <%=hb.getNames().get(i)%> </label>
                                    </span>
                                </div>
                            </div>

<%}%>
                </li>
                <br/>
                <br/>
                <label>Choose new detailed pictures</label>
            </div>
    <%}%>

    <script>
        function ajaxSubmit() {
            var AjaxURL= "/priceAjax.jsp";
            $.ajax({
                type: "POST",
                dataType: "html",
                url: AjaxURL  ,
                data: $('#houseForm').serialize(),
                success: function (result) {
                    var strresult=result.replace(/([^1-9.])+/,"");
                    alert(strresult);
                },
                error: function(data) {
                    alert("error:"+data.responseText);
                }
            });

        }
    </script>
    <br/>
    <input type = "button" onclick="addMore()" value="click to add a new file"/>
    <br/><br/>
        <li class="form-line" data-type="control_button" id="id_2">
            <div id="cid_2" class="form-input-wide">
                <div style="margin-left:156px;" class="form-buttons-wrapper">
                    <button id="input_2" type="submit" class="form-submit-button form-submit-button-wood_3d" data-component="button">
                        Submit Form
                    </button>
                </div>
            </div>
        </li>

        </ul>
        </div>
</form>
<script>
    function addMore() {
        var divm = document.createElement('div');
        divm.innerHTML = '</br>';
        var textnode = document.createTextNode('choose a file:');
        var newInput = document.createElement("input");
        newInput.type = 'file';
        newInput.name = "upload";
        divm.appendChild(textnode);
        divm.appendChild(newInput);
        document.getElementById("upload_form").appendChild(divm);
    }
</script>
</body>
</html>