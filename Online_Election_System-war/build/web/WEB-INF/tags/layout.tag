
<%@tag description="layout with side nav and dependencies" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html lang="en">
    <head>
        <jsp:include page="WEB-INF/includes/meta.jsp"></jsp:include>
        <jsp:include page="WEB-INF/includes/css_resources.jsp" ></jsp:include>
        <jsp:invoke fragment="header"/>
        <title>Online Voting System</title>
    </head>
        <body>
            <div class="flex">
                <div id="sidebar ">

                <jsp:include page="WEB-INF/includes/side_navigation.jsp"></jsp:include>
                </div>
                <div id="pageContent" class="w-full overflow-hidden">
                <jsp:doBody/>
            </div>
        </div>

        <div id="pagefooter">
            <jsp:include page="WEB-INF/includes/js_resources.jsp"></jsp:include>
            <jsp:invoke fragment="footer"/>
        </div>
    </body>
</html>
