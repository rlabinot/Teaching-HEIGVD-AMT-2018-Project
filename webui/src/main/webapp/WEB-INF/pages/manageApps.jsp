<%@include file="includes/header.jsp" %>

    <%@include file="includes/leftNavbar.jsp" %>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">

            <!-- Top line nav -->
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Manage your apps</h4>
                </div>
            </div>
            <!-- ./top line -->


            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <div class="clearfix">
                            <h3 class="box-title pull-left  ">Your applications</h3>
                            <a href="/webui/registerapp" class="btn pull-left m-l-20 btn-rounded btn-outline waves-effect waves-light btn-success">Add an application</a>
                        </div>

                        <!-- App Tab -->
                        <div class="table-responsive">
                            <table class="table" id="table">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>API Key</th>
                                    <th>Actions</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${apps}" var="app">
                                    <tr>
                                        <td>${app.name}</td>
                                        <td>${app.description}</td>
                                        <td>${app.apiKey}</td>
                                        <td><a href="/webui/editapp?id=${app.id}">Edit</a></td>
                                        <td><a href="/webui/deleteapp?id=${app.id}">Delete</a></td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <!-- ./app tab -->

                    </div>
                </div>
            </div>
        </div>

<%@include file="includes/footer.jsp" %>
