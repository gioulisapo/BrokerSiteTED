<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf"%>
<html>
<head>
    <title>Welcome</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Css -->
    <link href="css/index.css" rel="stylesheet">
</head>
    <body>
        <div class="loginContainer">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4">
                    <div class="account-wall">
                        <form class="form-signin" action="ServletLogin" method="post">
                            <input type="text" class="form-control" placeholder="Username" name="InputUsername" required autofocus>
                            <input type="password" class="form-control" placeholder="Password" name="InputPass" required>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                            <span class="clearfix"></span>
                        </form>
                    </div>
                    <a href="./signup.jsp" class="text-center new-account">Create an account </a>
                </div>
            </div>
        </div>
    </body>
</html>