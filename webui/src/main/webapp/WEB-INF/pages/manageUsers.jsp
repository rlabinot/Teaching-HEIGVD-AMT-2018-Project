<%@include file="includes/header.jsp" %>

    <%@include file="includes/leftNavbar.jsp" %>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">

            <!-- Top line bar -->
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Manage your apps</h4>
                </div>
            </div>
            <!-- ./top-line -->

            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <h3 class="box-title">Users list</h3>

                        <!-- User tab -->
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Mail</th>
                                    <th>Role</th>
                                    <th>State</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Ludwig</td>
                                    <td>loic,frueh@gmail.com</td>
                                    <td>Admin</td>
                                    <td>Active</td>
                                    <td><a href="#">Suspend</a></td>
                                    <td><a href="#">Reset password</a></td>
                                    <td><a href="#">Delete</a></td>
                                </tr>

                                <c:forEach items="${users}" var="user">
                                    <tr>
                                        <td>${user.name}</td>
                                        <td>${user.email}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.isAdmin}">Admin</c:when>
                                                <c:otherwise>User</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.isActive}">Active</c:when>
                                                <c:otherwise>Inactive</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td><a href="/webui/suspenduser?id=${user.id}">Suspend</a></td>
                                        <td><a href="/webui/resetpassword?id=${user.id}">Reset password</a></td>
                                        <td><a href="/webui/deleteuser?id=${user.id}">Delete</a></td>
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
