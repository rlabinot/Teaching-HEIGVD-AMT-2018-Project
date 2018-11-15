<div class="pageDisplayer">
    <c:if test="${pageTitle == 'Manage Apps'}">
        <p>Application ${pageIndex * pageSize + 1} to ${(pageIndex * pageSize + fn:length(apps))} &#151; Page ${pageIndex + 1} of ${pageCount}</p>
    </c:if>
    <c:if test="${pageTitle == 'Manage Users'}">
        <p>User ${pageIndex * pageSize + 1} to ${(pageIndex * pageSize + fn:length(users))} &#151; Page ${pageIndex + 1} of ${pageCount}</p>
    </c:if>
    <c:if test="${pageTitle == 'Logs Page'}">
        <p>Log ${pageIndex * pageSize + 1} to ${(pageIndex * pageSize + fn:length(logs))} &#151; Page ${pageIndex + 1} of ${pageCount}</p>
    </c:if>
</div>