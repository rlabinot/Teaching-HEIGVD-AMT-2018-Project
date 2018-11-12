<%@include file="includes/header.jsp" %>

    <%@include file="includes/leftNavbar.jsp" %>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">

            <!-- Top line bar -->
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Manage your users</h4>
                </div>
            </div>
            <!-- ./top-line -->

            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <h3 class="box-title">Users list</h3>

                        <!-- User tab -->
                        <div class="table-responsive">
                            <table class="table" id="table">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Mail</th>
                                    <th>Role</th>
                                    <th>State</th>
                                    <th>Password</th>
                                    <th>Actions</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${users}" var="user">
                                    <tr>
                                        <c:choose>
                                            <c:when test="${user.admin}">
                                                <td>${user.name}</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><a href="/webui/user?action=listapp&id=${user.email}">${user.name}</a></td>
                                            </c:otherwise>
                                        </c:choose>

                                        <td>${user.email}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.admin}">Admin</c:when>
                                                <c:otherwise>User</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.active}">Active</c:when>
                                                <c:otherwise>Inactive</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.mustChangePassword}">Resetting</c:when>
                                                <c:otherwise>Ok</c:otherwise>
                                            </c:choose>

                                        </td>
                                        <c:choose>
                                            <c:when test="${user.active}">
                                                <td><a id="buttonSuspend" href="/webui/user?action=suspend&id=${user.email}">Suspend</a></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><a id="buttonActivate" href="/webui/user?action=activate&id=${user.email}">Activate</a></td>
                                            </c:otherwise>
                                        </c:choose>

                                        <td><a id="buttonResetPassword" href="/webui/user?action=changePassword&id=${user.email}">Reset password</a></td>
                                        <td><a id="buttonDelete" href="/webui/user?action=delete&id=${user.email}">Delete</a></td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <!-- ./user-tab -->

                    </div>
                </div>
            </div>
        </div>

<%@include file="includes/footer.jsp" %>
