<%--
  Created by IntelliJ IDEA.
  User: soospawork
  Date: 2018-05-11
  Time: 오후 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<script>
    function alertFunction() {
        var form = document.getElementById("signupForm");

        alert(form.action+ " " + form.method + "   submit2");
        form.submit();
    }
</script>
<body>
<form class="signup-form" id="signupForm" action="/user/saveGeneral" method="POST">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <div class="row">
        <div class="input-field col s12">
            <input id="userId" name="userId" type="text" class="validate"/>
            <label for="userId">User Id</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <input id="name" name="name" type="text" class="validate"/>
            <label for="name">name</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <input id="password" name="password" type="password" class="validate"/>
            <label for="password">Password</label>
        </div>
    </div>
    <input class="signup-btn waves-effect waves-light btn" type="button" value="가입하기2" id="submitButton" onclick="alertFunction();"/>
</form>
</body>
</html>
