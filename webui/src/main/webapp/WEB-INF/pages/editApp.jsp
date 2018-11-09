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

                    <!-- App Registration Form -->
                    <form method="post" action="/app?action=edit&id=${app.id}" class="form-horizontal form-material">

                        <div class="form-group">
                            <label class="col-md-12">Name</label>
                            <div class="col-md-12">
                                <input type="text" name="name" value="${app.name}" class="form-control form-control-line">
                                <c:if test="${inputError.emptyName}">
                                    <span>${inputError.emptyFieldMessage}</span>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-12">Description</label>
                            <div class="col-md-12">
                                <input type="text" value="${app.description}" name="description" class="form-control form-control-line">
                                <c:if test="${inputError.emptyDescription}">
                                    <span>${inputError.emptyFieldMessage}</span>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="submit" value="Edit" class="btn btn-success"/>
                            </div>
                        </div>

                    </form>
                    <!-- ./form -->

                </div>
            </div>
        </div>
    </div>

<%@include file="includes/footer.jsp" %>