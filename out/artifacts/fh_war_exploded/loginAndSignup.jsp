<%--
  Created by IntelliJ IDEA.
  User: lycbel
  Date: 11/3/2017
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="controls.*" %>
<%@page import="modules.*" %>
<%@ page import="javax.jws.Oneway" %>
<%@ page import="DB.OwnerDB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Sign-Up/Login Form</title>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">


    <link rel="stylesheet" href="/css/login.css">


</head>

<body>
<h><%=new OwnerDB().getS()%></h>

<%if(null!=request.getParameter("type")) {
    int type = Integer.parseInt(request.getParameter("type"));
    AuthControl ac = new AuthControl();
    //sign up
    if(type == 0){
        String  f= request.getParameter("firstName");
        String  l= request.getParameter("lastName");
        String  e= request.getParameter("email");
        String  p= request.getParameter("password");
        String  pc= request.getParameter("passwordConfirm");
        User us = new User(f,l,e,p,pc);
        String result = ac.signup(us);

        %>
 <h1><%=result%></h1>
<%
}
    //login
    if(type == 2){
        String  p= request.getParameter("password");
        String  e= request.getParameter("email");
        User u = ac.login(e,p);
        if(u!=null) {
            session.setAttribute("logged", 1);
            session.setAttribute("user", u);
            response.sendRedirect("/index.jsp");
        }
    }
}
%>
<div class="form">

    <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
    </ul>

    <div class="tab-content">
        <div id="signup">
            <h1>Sign Up for Free</h1>

            <form action="/loginAndSignup.jsp?type=0" method="post">

                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            First Name<span class="req">*</span>
                        </label>
                        <input type="text" required autocomplete="off" name="firstName" />
                    </div>

                    <div class="field-wrap">
                        <label>
                            Last Name<span class="req">*</span>
                        </label>
                        <input type="text"required autocomplete="off" name = "lastName"/>
                    </div>
                </div>

                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="email"required autocomplete="off" name="email"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Set A Password<span class="req">*</span>
                    </label>
                    <input type="password"required autocomplete="off" name="password"/>
                </div>
                <div class="field-wrap">
                    <label>
                        Confirm Password<span class="req">*</span>
                    </label>
                    <input type="password"required autocomplete="off" name="passwordConfirm"/>
                </div>
                <button type="submit" class="button button-block"/>Get Started</button>

            </form>

        </div>

        <div id="login">
            <h1>Welcome Back!</h1>

            <form action="/loginAndSignup.jsp?type=2" method="post">

                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="email"required autocomplete="off" name="email"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Password<span class="req">*</span>
                    </label>
                    <input type="password"required autocomplete="off" name="password"/>
                </div>

                <p class="forgot"><a href="#">Forgot Password?</a></p>

                <button class="button button-block"/>Log In</button>

            </form>

        </div>

    </div><!-- tab-content -->

</div> <!-- /form -->
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script  src="/src/login.js"></script>

</body>
</html>

