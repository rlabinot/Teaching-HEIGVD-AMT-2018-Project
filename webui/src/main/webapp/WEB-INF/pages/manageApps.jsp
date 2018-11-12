<%@include file="includes/header.jsp" %>
    <%@include file="includes/leftNavbar.jsp" %>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">

            <!-- Top line nav -->
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <c:choose>
                        <c:when test="${isAdmin}">
                            <h4 class="page-title">User login: ${user.email}</h4>
                        </c:when>
                        <c:otherwise>
                            <h4 class="page-title">Manage your apps</h4>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <!-- ./top line -->


            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <div class="clearfix">
                            <c:choose>
                                <c:when test="${isAdmin}">
                                    <h3 class="box-title pull-left  ">List of ${user.name}'s applications</h3>
                                    <a id="buttonAdd" href="/webui/home" class="btn pull-left m-l-20 btn-rounded btn-outline waves-effect waves-light btn-success">Return to admin panel</a>
                                </c:when>
                                <c:otherwise>
                                    <h3 class="box-title pull-left  ">Your applications</h3>
                                    <a id="buttonAdd" href="/webui/app" class="btn pull-left m-l-20 btn-rounded btn-outline waves-effect waves-light btn-success">Add an application</a>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <!-- App Tab -->
                        <div class="table-responsive">
                            <table class="table" id="table">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>API Key</th>
                                    <th>API Secret</th>
                                    <c:if test="!${isAdmin}">
                                        <th>Actions</th>
                                        <th></th>
                                        <th></th>
                                    </c:if>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${apps}" var="app">
                                    <tr>
                                        <td>${app.name}</td>
                                        <td>${app.description}</td>
                                        <td>${app.apiKey}</td>
                                        <td>${app.apiSecret}</td>
                                        <c:if test="!${isAdmin}">
                                            <td><a id="buttonShow" href="/webui/app?action=show&id=${app.id}">Show</a></td>
                                            <td><a id="buttonEdit" href="/webui/app?action=edit&id=${app.id}">Edit</a></td>
                                            <td><a id="buttonDelete" href="/webui/app?action=delete&id=${app.id}">Delete</a></td>
                                        </c:if>
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
