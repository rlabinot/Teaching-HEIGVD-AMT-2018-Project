<%@include file="includes/header.jsp" %>


    <!-- Page Content -->
    <div id="page-wrapper" class="index">
        <div class="container-fluid">

            <!-- Top line nav -->
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Login </h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="/webui">About</a></li>
                        <li class="active">Login</li>
                    </ol>
                </div>
            </div>
            <!-- ./line -->

            <div class="row">
                <div class="center-page col-md-8 col-xs-12">
                    <div class="white-box">

                        <!-- Login Form -->
                        <form method="post" class="form-horizontal form-material">
                            <div class="form-group">
                                <label class="col-md-12">E-mail</label>
                                <div class="col-md-12">
                                    <input type="email" class="form-control form-control-line" name="email">
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
                                    <a class="gap" href="/webui/registeruser">Register</a>
                                </div>
                            </div>
                        </form>
                        <!-- ./form -->

                    </div>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->

<%@include file="includes/footer.jsp" %>
