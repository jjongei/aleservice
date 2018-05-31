<html xmlns:th="http://www.thymeleaf.org">
<head th:include="layout :: head(title=~{::title},links=~{})">
    <title>Please Login</title>
</head>
<body th:include="layout :: body" th:with="content=~{::content}">
<div th:fragment="content">
    <form name="loginForm" id="loginForm" action="/login/process" method="POST">
        <fieldset>
            <legend>Please Login</legend>
            <div th:if="${param.error}" class="alert alert-error">
                Invalid username and password.
            </div>
            <div th:if="${param.logout}" class="alert alert-success">
                You have been logged out.
            </div>
            <label for="loginId">Username</label>
            <input type="text" id="loginId" name="loginId"/>
            <label for="loginPassword">Password</label>
            <input type="password" id="loginPassword" name="loginPassword"/>
            <div class="form-actions">
                <button type="submit" class="btn" onclick="formSubmit();">Log in</button>
            </div>
        </fieldset>
    </form>
</div>
<script>
    function formSubmit() {
        var varform = document.getElementById("loginForm");
        alert(varform.action + "   " + varform.method);
        varform.submit();
    }
</script>
</body>
</html>
