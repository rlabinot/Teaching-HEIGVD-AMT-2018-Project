<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse slimscrollsidebar">
        <ul class="nav" id="side-menu">
            <c:choose>
                <c:when test="${isAdmin}">
                    <li style="padding: 10px 0 0;">
                        <a id="menuItemUsers" href="/webui/home" class="waves-effect"><i class="fa fa-users fa-fw" aria-hidden="true"></i><span class="hide-menu">Users</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li style="padding: 10px 0 0;">
                        <a id="menuItemApps" href="/webui/home" class="waves-effect"><i class="fa fa-folder-open fa-fw" aria-hidden="true"></i><span class="hide-menu">Applications</span></a>
                    </li>
                </c:otherwise>
            </c:choose>

            <li>
                <a id="menuItemLogout" href="/webui/login?action=logout" class="waves-effect"><i class="fa fa-power-off fa-fw" aria-hidden="true"></i><span class="hide-menu">Logout</span></a>
            </li>
        </ul>
    </div>
</div>