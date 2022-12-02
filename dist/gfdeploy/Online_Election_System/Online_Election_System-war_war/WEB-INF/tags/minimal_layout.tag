
<%@tag description="layout with dependencies only" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html lang="en">
    <head>
        <jsp:include page="WEB-INF/includes/meta.jsp"></jsp:include>
        <jsp:include page="WEB-INF/includes/css_resources.jsp" ></jsp:include>
        <title>Online Voting System</title>

    </head>
    <body>
        <div class="flex">
            <div id="pageContent" class="w-full">
                <jsp:doBody/>
            </div>
        </div>
        <jsp:include page="WEB-INF/includes/js_resources.jsp"></jsp:include>
    </body>
</html>