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
    <c:when test="${isAdmin}">
        <script>
            $(document).ready( function () {
                $('#table').DataTable(

                    {
                        "ordering": false,
                        "language": {
                            "info": "Showing _START_ to _END_ of _TOTAL_ users",
                            "infoEmpty": "Showing 0 to 0 of 0 users",
                            "sEmptyTable": "There isn't any users",
                            "lengthMenu": "Show _MENU_  users",

                        }
                    }
                );
            } );
        </script>
    </c:when>

    <c:otherwise>
        <script>
            $(document).ready( function () {
                $('#table').DataTable(

                    {
                        "ordering": false,
                        "language": {
                            "info": "Showing _START_ to _END_ of _TOTAL_ applications",
                            "infoEmpty": "Showing 0 to 0 of 0 applications",
                            "sEmptyTable": "You haven't any applications",
                            "lengthMenu": "Show _MENU_  applications",
                        }
                    }
                );
            } );
        </script>
    </c:otherwise>

</c:choose>

</body>

</html>
