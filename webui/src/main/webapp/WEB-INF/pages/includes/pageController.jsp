<nav>
    <ul class="pager">
        <c:if test="${pageCount != 0}">
            <c:if test="${pageIndex != 0}">
                <li><a id="buttonBegin" href="<c:url value="${firstPageLink}"/>">First</a></li>
                <li><a id="buttonPrevious" href="<c:url value="${prevPageLink}"/>">&#9666; Previous</a></li>
            </c:if>
            <c:if test="${pageIndex != pageCount - 1}">
                <li><a id="buttonNext" href="<c:url value="${nextPageLink}"/>">Next &#9656;</a></li>
                <li><a id="buttonEnd" href="<c:url value="${lastPageLink}"/>">Last</a></li>
            </c:if>
        </c:if>
    </ul>
</nav>