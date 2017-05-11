<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <title>Projects</title>

    <!-- Bootstrap core CSS -->
    <%--<link href="blank_files/bootstrap.css" rel="stylesheet">--%>
    <link href="bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="startPage.jsp">BugTracker</a>
        </div>

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="startPage.jsp">Home</a></li>
                <li class="dropdown">
                    <a href="projects.jsp" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Projects <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="createProject.jsp">Create</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="accounts.jsp" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Accounts <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="createAccount.jsp">Create</a></li>
                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="about.jsp">About</a></li>
                <li class="dropdown">
                    <a href="accounts.jsp" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <span class="glyphicon glyphicon-user"></span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="accounts.jsp">Profile</a></li>
                        <li><a href="#">Another action</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="login.jsp">Log out</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li>
                    <div class="input-group input-group-sm">
                        <input type="text" class="form-control" placeholder="Search">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                        </div>
                    </div>
                </li>
                <%--<li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>--%>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="">Nav item</a></li>
                <li><a href="">Nav item again</a></li>
                <li><a href="">One more nav</a></li>
                <li><a href="">Another nav item</a></li>
                <li><a href="">More navigation</a></li>
            </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Project</h1>

            <form action="" role="form" class="form-horizontal">
                <div class="form-group row">
                    <div class="col-xs-offset-0 col-xs-7">
                        <button type="submit" class="btn btn-default">Edit</button>
                        <button type="submit" class="btn btn-default pad">Delete</button>
                        <a href="">Create a task</a>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-xs-7">
                        <label for="projectName">Project name:</label>
                        <input type="text" class="form-control" id="projectName">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-7">
                        <label for="headOfPrj">Head of project:</label>
                        <input type="text" class="form-control" id="headOfPrj">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-7">
                        <label for="dateStart">Start date:</label>
                        <input type="date" class="form-control" id="dateStart">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-7">
                        <label for="dateFinish">Finish date:</label>
                        <input type="date" class="form-control" id="dateFinish">
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-xs-offset-0 col-xs-7">
                        <button type="submit" class="btn btn-default">Cancel</button>
                        <button type="submit" class="btn btn-default pad">Save</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="./js/jquery.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="./js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>
