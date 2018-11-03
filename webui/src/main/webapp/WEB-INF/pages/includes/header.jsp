<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <base href="${pageContext.request.contextPath}/"/>
    <title>${pageTitle}</title>
    <!-- jQuery -->
    <script src="static/vendor/plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="static/vendor/plugins/images/favicon.png">
    <!-- Bootstrap Core CSS -->
    <link href="static/vendor/html/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="static/vendor/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- Animation CSS -->
    <link href="static/vendor/html/css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="static/vendor/html/css/style.css" rel="stylesheet">
    <link href="static/css/myStyle.css" rel="stylesheet">
</head>

<body>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top m-b-0">
        <div class="navbar-header"> <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="/webui" data-toggle="collapse" data-target=".navbar-collapse"><i class="fa fa-bars"></i></a>
            <div class="top-left-part">
                <a class="logo" href="/webui">
                    <b><img src="static/vendor/plugins/images/pixeladmin-logo.png" alt="home" /></b>
                    <span class="hidden-xs"><img src="static/vendor/plugins/images/pixeladmin-text.png" alt="home" /></span>
                </a>
            </div>
        </div>
    </nav>

