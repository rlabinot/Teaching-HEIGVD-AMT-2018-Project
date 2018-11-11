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
                                    <input id="inputName" type="text" name="name" class="form-control form-control-line">
                                    <c:if test="${inputError.emptyName}">
                                        <span class="errors">${inputError.emptyFieldMessage}</span>
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-12">E-mail</label>
                                <div class="col-md-12">
                                    <input id="inputEmail" type="email" name="email" class="form-control form-control-line">
                                    <c:if test="${inputError.emptyEmail}">
                                        <span class="errors">${inputError.emptyFieldMessage}</span>
                                    </c:if>
                                    <c:if test="${inputError.wrongFormatEmail}">
                                        <span class="errors">${inputError.wrongFormatEmailMessage}</span>
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="password" class="col-md-12">Password</label>
                                <div class="col-md-12">
                                    <input id="inputPassword" type="password" class="form-control form-control-line" name="password">
                                    <c:if test="${inputError.emptyPassword}">
                                        <span class="errors">${inputError.emptyFieldMessage}</span>
                                    </c:if>
                                    <c:if test="${inputError.weakPassword}">
                                        <span class="errors">${inputError.weakPasswordMessage}</span>
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-12">
                                    <input id="buttonRegister" type="submit" value="Register" class="btn btn-success"/>
                                </div>
                            </div>
                        </form>
                        <!-- ./form -->

                    </div>
                </div>
            </div>
        </div>

<%@include file="includes/footer.jsp" %>
