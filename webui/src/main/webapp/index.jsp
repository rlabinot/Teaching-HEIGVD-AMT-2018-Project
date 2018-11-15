<%@include file="/WEB-INF/pages/includes/header.jsp" %>

    <!-- Page Content -->
    <div id="page-wrapper" class="index">
        <div class="container-fluid">

            <!-- Top line nav -->
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">About </h4> </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a id="buttonLogin" href="/webui/login">Login</a></li>
                        <li><a id="buttonRegister" href="/webui/user">Register</a></li>
                    </ol>
                </div>
            </div>
            <!-- ./top-line -->

            <!-- About content -->
            <div class="row">
                <div class="col-md-12">
                    <div class="white-box">
                        <h2><b>Teaching-HEIGVD-AMT-2018-Project</b></h2><br>
                        <h3>Welcome !<br><br>
                            This web application is the first part of a gamification engine project, you can find its official repository <a href="">here</a>.<br>
                            The basic functions are the following :<br>
                            - As a simple user you can register, log in and add/edit/delete your applications. The web application will give you an API Key and an API Secret.<br>
                            - As an admin you can manage the website. An admin has the possibility to suspend users, look at their applications, reset their password or check the logs of the web application.<br>
                            The project is working with a Payara application server and a MySQL database maintained with PHPMyAdmin.</h3>
                    </div>
                </div>
            </div>
            <!-- ./content -->

        </div>

<%@include file="/WEB-INF/pages/includes/footer.jsp" %>

