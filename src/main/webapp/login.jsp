<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <title>Sign In</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="js/scripts.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">

    <form id="loginForm" action="login" class="form-signin" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input id="inputEmail" name="userEmail" class="form-control" placeholder="Email address" required="" autofocus="" type="email">

        <label for="inputPassword" class="sr-only">Password</label>
        <input id="inputPassword" name="userPassword" class="form-control" placeholder="Password" required="" type="password">
        <div class="checkbox">
            <label>
                <input value="remember-me" type="checkbox"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" value="login">Log in</button>
    </form>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="./js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
