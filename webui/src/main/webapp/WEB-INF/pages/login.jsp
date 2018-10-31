<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Pixel Admin - Login</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="vendor/plugins/images/favicon.png">
    <!-- Bootstrap Core CSS -->
    <link href="vendor/html/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="vendor/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- Animation CSS -->
    <link href="vendor/html/css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="vendor/html/css/style.css" rel="stylesheet">
    <link href="css/myStyle.css" rel="stylesheet">
</head>

<body>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top m-b-0">
        <div class="navbar-header"> <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse"><i class="fa fa-bars"></i></a>
            <div class="top-left-part">
                <a class="logo" href="index.html">
                    <b><img src="vendor/plugins/images/pixeladmin-logo.png" alt="home" /></b>
                    <span class="hidden-xs"><img src="vendor/plugins/images/pixeladmin-text.png" alt="home" /></span>
                </a>
            </div>
        </div>
    </nav>



    <!-- Page Content -->
    <div id="page-wrapper" class="index">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb login">
                        <li><a href="/">About</a></li>
                        <li class="active">Login</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="center-page col-md-8 col-xs-12">
                    <div class="white-box">
                        <form method="post" action="/login" class="form-horizontal form-material">
                            <div class="form-group">
                                <label class="col-md-12">E-mail</label>
                                <div class="col-md-12">
                                    <input type="email" class="form-control form-control-line">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-md-12">Password</label>
                                <div class="col-md-12">
                                    <input type="password" class="form-control form-control-line" name="password" id="password">
                                    <c:if test="${inputError.wrongLogin}">
                                        <span>${inputError.wrongLoginMessage}</span>
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="submit" value="Login" class="btn btn-success"/>
                                    <a class="gap" href="#">Register</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
        <footer class="footer index text-center"> 2017 &copy; Pixel Admin brought to you by wrappixel.com </footer>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<!-- jQuery -->
<script src="vendor/plugins/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="vendor/html/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Menu Plugin JavaScript -->
<script src="vendor/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
<!--slimscroll JavaScript -->
<script src="vendor/html/js/jquery.slimscroll.js"></script>
<!--Wave Effects -->
<script src="vendor/html/js/waves.js"></script>
<!-- Custom Theme JavaScript -->
<script src="vendor/html/js/custom.min.js"></script>
</body>

</html>