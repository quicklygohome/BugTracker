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

    <title>Task</title>

    <!-- Bootstrap core CSS -->
    <%--<link href="blank_files/bootstrap.css" rel="stylesheet">--%>
    <link href="bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/dashboard.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="./css/commentBox.css">

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
            <%--left navbar--%>
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
                    <li>
                        <div class="checkbox">
                            <label><input type="checkbox" name="usualSrch" checked>Usual search</label>
                        </div>
                    </li>
                    <li>
                        <div class="checkbox">
                            <label><input type="checkbox" name="similarSrch">Search for similar task</label>
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
                <h1 class="page-header">TaskName</h1>

                <%--table--%>
                <div class="table-responsive" style="overflow-y: visible !important;">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Type</th>
                                <th>State</th>
                                <th>Priority</th>
                                <th>Assignee</th>
                                <th>Reporter</th>
                                <th>Created</th>
                                <th>Updated</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="dropdown">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Component <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Bug</a></li>
                                        <li><a href="#">Feature</a></li>
                                        <li><a href="#">Component</a></li>
                                    </ul>
                                </td>
                                <td class="dropdown">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Opened <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Opened</a></li>
                                        <li><a href="#">Midflow</a></li>
                                        <li><a href="#">Resolved</a></li>
                                        <li><a href="#">Closed</a></li>
                                    </ul>
                                </td>
                                <td class="dropdown">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Urgent <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Urgent</a></li>
                                        <li><a href="#">Major</a></li>
                                        <li><a href="#">Normal</a></li>
                                        <li><a href="#">Low</a></li>
                                    </ul>
                                </td>
                                <td>
                                    <div class="input-group input-group-sm">
                                        <label for="assignee"></label>
                                        <input type="text" class="form-control" id="assignee">
                                        <div class="input-group-btn">
                                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-pencil"></i></button>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group input-group-sm">
                                        <label for="reporter"></label>
                                        <input type="text" class="form-control" id="reporter">
                                        <div class="input-group-btn">
                                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-pencil"></i></button>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group input-group-sm">
                                        <label for="dateCreated"></label>
                                        <input type="datetime" class="form-control" id="dateCreated">
                                        <div class="input-group-btn">
                                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-calendar"></i></button>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group input-group-sm">
                                        <label for="dateUpdated"></label>
                                        <input type="datetime" class="form-control" id="dateUpdated">
                                        <div class="input-group-btn">
                                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-calendar"></i></button>
                                        </div>
                                    </div>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>

                <%--description box--%>
                <form>
                    <div class="form-group col-xs-7">
                        <label for="description"><h4>Description</h4></label>
                        <textarea class="form-control" rows="5" id="description"></textarea>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-0 col-xs-7">
                            <button type="submit" class="btn btn-default top20">Edit description</button>
                        </div>
                    </div>
                </form>

                <%--comment box--%>
                <form>
                    <div class="form-group col-sm-7">
                        <div class="panel panel-white post panel-shadow">
                            <div class="post-heading">
                                <div class="pull-left image">
                                    <img src="http://bootdey.com/img/Content/user_1.jpg" class="img-circle avatar" alt="user profile image">
                                </div>
                                <div class="pull-left meta">
                                    <div class="title h5">
                                        <a href="#"><b>Ryan Haywood</b></a>
                                        made a post.
                                    </div>
                                    <h6 class="text-muted time">1 minute ago</h6>
                                </div>
                            </div>
                            <div class="post-description">
                                <p>Bootdey is a gallery of free snippets resources templates and utilities for bootstrap css hmtl js framework. Codes for developers and web designers</p>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-sm-7">
                        <div class="panel panel-white post panel-shadow">
                            <div class="post-heading">
                                <div class="pull-left image">
                                    <img src="http://bootdey.com/img/Content/user_1.jpg" class="img-circle avatar" alt="user profile image">
                                </div>
                                <div class="pull-left meta">
                                    <div class="title h5">
                                        <a href="#"><b>Ryan Haywood</b></a>
                                        made a post.
                                    </div>
                                    <h6 class="text-muted time">1 minute ago</h6>
                                </div>
                            </div>
                            <div class="post-description">
                                <p>Bootdey is a gallery of free snippets resources templates and utilities for bootstrap css hmtl js framework. Codes for developers and web designers</p>
                            </div>
                        </div>
                    </div>

                    <div class="form-group col-xs-7">
                        <label for="comment"><h3>Comment</h3></label>
                        <textarea class="form-control" rows="3" id="comment"></textarea>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-0 col-xs-7">
                            <button type="submit" class="btn btn-default">Add a comment</button>
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
