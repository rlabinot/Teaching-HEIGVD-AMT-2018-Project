<footer class="footer index text-center"> 2018 &copy; Appadmin </footer>
</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<!-- Bootstrap Core JavaScript -->
<script src="static/vendor/html/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Menu Plugin JavaScript -->
<script src="static/vendor/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
<!--slimscroll JavaScript -->
<script src="static/vendor/html/js/jquery.slimscroll.js"></script>
<!--Wave Effects -->
<script src="static/vendor/html/js/waves.js"></script>
<!-- Custom Theme JavaScript -->
<script src="static/vendor/html/js/custom.min.js"></script>

<script>
    function run() {
        var $pageSize = $('#pageSize').val();
        <c:choose>
            <c:when test="${pageTitle == 'Logs Page'}">
                window.location.assign("${pageContext.request.contextPath}/log?pageSize=" + $pageSize + "&pageIndex=${pageIndex}");
            </c:when>
            <c:when test="${(pageTitle == 'Manage Apps') && (isAdmin)}">
                window.location.assign("${pageContext.request.contextPath}/user?action=listapp&id=${user.email}&pageSize=" + $pageSize + "&pageIndex=${pageIndex}");
            </c:when>
            <c:otherwise>
                window.location.assign("${pageContext.request.contextPath}/home?pageSize=" + $pageSize + "&pageIndex=${pageIndex}");
            </c:otherwise>
        </c:choose>
    }

</script>
</body>

</html>
