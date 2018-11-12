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

<c:choose>
    <c:when test="${pageTitle == 'Manage Apps'}">
        <c:set var = "items" value = "applications"/>
    </c:when>

    <c:when test="${pageTitle == 'Manage Users'}">
        <c:set var = "items" value = "users"/>
    </c:when>
</c:choose>

<script>
    $(document).ready( function () {
        $('#table').DataTable(

            {
                "ordering": false,
                "language": {
                    "info": "Showing _START_ to _END_ of _TOTAL_ ${items}",
                    "infoEmpty": "Showing 0 to 0 of 0 ${items}",
                    "sEmptyTable": "There isn't any ${items}",
                    "lengthMenu": "Show _MENU_  ${items}",

                }
            }
        );
    } );
</script>

</body>

</html>
