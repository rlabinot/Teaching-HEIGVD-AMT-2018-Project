<%@include file="includes/header.jsp" %>



    <!-- Page Content -->
    <div id="page-wrapper" class="index">
        <div class="container-fluid">

            <!-- Top line nav -->
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Register</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="/webui">About</a></li>
                        <li class="active">Register</li>
                    </ol>
                </div>
            </div>
            <!-- ./top line -->

            <div class="row">
                <div class="center-page col-md-8 col-xs-12">
                    <div class="white-box">

                        <!-- Register Form -->
                        <form method="post" class="form-horizontal form-material">

                            <div class="form-group">
                                <label class="col-md-12">Name</label>
                                <div class="col-md-12">
                                    <input type="text" name="name" class="form-control form-control-line">
                                    <c:if test="${inputError.emptyName}">
                                        <span>${inputError.emptyFieldMessage}</span>
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-12">E-mail</label>
                                <div class="col-md-12">
                                    <input type="email" name="email" class="form-control form-control-line">
                                    <c:if test="${inputError.emptyEmail}">
                                        <span>${inputError.emptyFieldMessage}</span>
                                    </c:if>
                                    <c:if test="${inputError.wrongFormatEmail}">
                                        <span>${inputError.wrongFormatEmailMessage}</span>
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="password" class="col-md-12">Password</label>
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
                                <div class="col-sm-12">
                                    <input type="submit" value="Register" class="btn btn-success"/>
                                </div>
                            </div>
                        </form>
                        <!-- ./form -->

                    </div>
                </div>
            </div>
        </div>

<%@include file="includes/footer.jsp" %>
