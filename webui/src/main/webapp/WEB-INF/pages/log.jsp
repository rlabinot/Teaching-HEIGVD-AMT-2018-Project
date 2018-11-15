<%@include file="includes/header.jsp" %>

<%@include file="includes/leftNavbar.jsp" %>

<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">

        <!-- Top line bar -->
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">This is the Logs</h4>
            </div>
        </div>
        <!-- ./top-line -->

        <div class="row">
            <div class="col-sm-12">
                <div class="white-box">
                    <div class="flex">
                        <div class="option-line">
                            <h3 class="box-title">Show</h3>
                            <div id="selector">
                                <select id="pageSize" onchange="run()">
                                    <option <c:if test="${pageSize == 10}">selected</c:if> value="10">10</option>
                                    <option <c:if test="${pageSize == 20}">selected</c:if> value="20">20</option>
                                    <option <c:if test="${pageSize == 50}">selected</c:if> value="50">50</option>
                                    <option <c:if test="${pageSize == 100}">selected</c:if> value="100">100</option>
                                </select>
                            </div>
                            <h3 class="box-title">logs</h3>
                        </div>
                        <%@include file="includes/paginationDisplayer.jsp" %>
                        <%@include file="includes/pageController.jsp" %>
                    </div>

                    <!-- User tab -->
                    <div class="table-responsive">
                        <table class="table" id="table">
                            <thead>
                            <tr>
                                <th>Date</th>
                                <th>User</th>
                                <th>Status</th>
                                <th>Action</th>
                                <th>Description</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${logs}" var="log">
                                <tr>
                                    <jsp:useBean id="dateObject" class="java.util.Date" />
                                    <jsp:setProperty name="dateObject" property="time" value="${log.date}" />
                                    <td><fmt:formatDate type = "both" value = "${dateObject}" /></td>
                                    <td>${log.user}</td>
                                    <td>${log.status}</td>
                                    <td>${log.action}</td>
                                    <td>${log.description}</td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        <div class="flex">
                            <%@include file="includes/paginationDisplayer.jsp" %>
                            <%@include file="includes/pageController.jsp" %>
                        </div>
                    </div>
                    <!-- ./user-tab -->

                </div>
            </div>
        </div>
    </div>

    <%@include file="includes/footer.jsp" %>
