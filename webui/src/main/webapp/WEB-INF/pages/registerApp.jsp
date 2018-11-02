<%@include file="includes/header.jsp" %>

    <!-- Left navbar-header -->
    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse slimscrollsidebar">
            <ul class="nav" id="side-menu">
                <li style="padding: 10px 0 0;">
                    <a href="/webui/home" class="waves-effect"><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a>
                </li>
            </ul>
        </div>
    </div>
    <!-- Left navbar-header end -->
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Register your app</h4> </div>

                <!-- /.col-lg-12 -->
            </div>
            <!-- /row -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <form method="post" action="/webui/registerapp" class="form-horizontal form-material">
                            <div class="form-group">
                                <label class="col-md-12">Name</label>
                                <div class="col-md-12">
                                    <input type="text" class="form-control form-control-line">
                                    <c:if test="${inputError.emptyName}">
                                        <span>${inputError.emptyFieldMessage}</span>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Description</label>
                                <div class="col-md-12">
                                    <input type="text" class="form-control form-control-line">
                                    <c:if test="${inputError.emptyDescription}">
                                        <span>${inputError.emptyFieldMessage}</span>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">API Key</label>
                                <div class="col-md-12">
                                    <input type="text" class="form-control form-control-line">
                                    <c:if test="${inputError.emptyApiKey}">
                                        <span>${inputError.emptyFieldMessage}</span>
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-12">API Secret</label>
                                <div class="col-md-12">
                                    <input type="password" class="form-control form-control-line">
                                    <c:if test="${inputError.emptyApiSecret}">
                                        <span>${inputError.emptyFieldMessage}</span>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input type="submit" value="Register" class="btn btn-success"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->

<%@include file="includes/footer.jsp" %>