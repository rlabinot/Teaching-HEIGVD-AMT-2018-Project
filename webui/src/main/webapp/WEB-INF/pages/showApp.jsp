<%@include file="includes/header.jsp" %>

<%@include file="includes/leftNavbar.jsp" %>

<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">

        <!-- Top line nav -->
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">Edit your app</h4>
            </div>
        </div>
        <!-- ./top line -->

        <div class="row">
            <div class="col-sm-12">
                <div class="white-box">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Name</th>
                            </tr>
                            </thead>

                            <tbody>
                            <tr>
                                <td>${app.name}</td>
                            </tr>
                            </tbody>

                            <thead>
                            <tr>
                                <th>Description</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${app.description}</td>
                            </tr>
                            </tbody>

                            <thead>
                            <tr>
                                <th>API Key</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${app.apiKey}</td>
                            </tr>
                            </tbody>

                            <thead>
                            <tr>
                                <th>API Secret</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${app.apiSecret}</td>
                            </tr>
                            </tbody>

                        </table>
                    </div>
                    <a href="/webui/app?action=edit&id=${app.id}" class="btn btn-success link">Edit</a>
                    <a href="/webui/app?action=delete&id=${app.id}" class="btn-danger btn link">Delete</a>
                </div>
            </div>
        </div>
    </div>

<%@include file="includes/footer.jsp" %>