<%@include file="includes/header.jsp" %>


<!-- Page Content -->
<div id="page-wrapper" class="index">
    <div class="container-fluid">

        <!-- Top line nav -->
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">Change your password</h4>
            </div>
        </div>
        <!-- ./line -->

        <div class="row">
            <div class="center-page col-md-8 col-xs-12">
                <div class="white-box">

                    <!-- Login Form -->
                    <form method="post" action="webui/user?action=changePassword" class="form-horizontal form-material">

                        <div class="form-group">
                            <label for="password" class="col-md-12">New password</label>
                            <div class="col-md-12">
                                <input type="password" class="form-control form-control-line" name="password" id="password">
                                <c:if test="${inputError.emptyPassword}">
                                    <span>${inputError.emptyFieldMessage}</span>
                                </c:if>
                                <c:if test="${inputError.weakPassword}">
                                    <span>${inputError.weakPasswordMessage}</span>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password2" class="col-md-12">Confirm new password</label>
                            <div class="col-md-12">
                                <input type="password" class="form-control form-control-line" name="password2" id="password2">
                                <c:if test="${inputError.emptyPassword}">
                                    <span>${inputError.emptyFieldMessage}</span>
                                </c:if>
                                <c:if test="${inputError.weakPassword}">
                                    <span>${inputError.weakPasswordMessage}</span>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="submit" value="Continue" class="btn btn-success"/>
                            </div>
                        </div>
                    </form>
                    <!-- ./form -->

                </div>
            </div>
        </div>
    </div>

    <%@include file="includes/footer.jsp" %>
