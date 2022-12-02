
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>

<jsp:include page="WEB-INF/includes/components/new_election_modal.jsp"></jsp:include>
<t:layout>
    <jsp:attribute name="header"></jsp:attribute>
    <jsp:attribute name="footer"></jsp:attribute>

    <jsp:body>
        <jsp:include page="WEB-INF/includes/components/hero.jsp">
            <jsp:param name="title" value="Election" />
            <jsp:param name="fromColour" value="from-emerald-400" />
            <jsp:param name="toColour" value="to-emerald-100" />
            <jsp:param name="type" value="election" /> 
        </jsp:include>

        <div class="m-3 py-3 px-10">
            <c:choose>
                <c:when test="${ongoingElection != null}">
                    <div class="currentElection flex justify-center w-6/12 m-auto">
                        <div class="block p-6 rounded-lg shadow-lg bg-white grow">
                            <h5 class="text-gray-900 text-3xl leading-tight font-medium mb-2">${ongoingElection.title}</h5>
                            <div class="rounded text-xl text-base mb-4 bg-gray-200 p-7">
                                <p>Start date: ${ongoingElection.startDate}</p>
                                <p>End date: ${ongoingElection.endDate}</p>
                            </div>
                            <form method="POST" action="ElectionController">
                                <input type="hidden" name="electionAction" value="FIND_SELECTED_ELECTION"  />
                                <input type="hidden" name="election_id" value="${ongoingElection.id}" />
                                <button class="block mx-auto w-6/12 px-6 py-2 border-2 border-blue-600 text-blue-600 font-medium text-xs leading-tight uppercase rounded hover:bg-blue-600 hover:text-white focus:outline-none focus:ring-0 transition duration-150 ease-in-out">View</button>
                            </form>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>There is no ongoing election at the moment.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="grid grid-cols-4 gap-x-6 gap-y-4 my-10 px-7">
            <c:forEach items="${finishedElections}" var="election">
                <div class="flex justify-center" data-id="${election.id}">
                    <div class="block p-6 rounded-lg shadow-lg bg-white">
                        <h5 class="text-gray-900 text-xl leading-tight font-medium mb-2">${election.title}</h5>
                        <div class="rounded text-xl text-base mb-4 bg-gray-200 p-7">
                            <p>Start date: ${election.startDate}</p>
                            <p>End date: ${election.endDate}</p>
                        </div>
                        <form method="POST" action="ElectionController">
                            <input type="hidden" name="electionAction" value="FIND_SELECTED_ELECTION"  />
                            <input type="hidden" name="election_id" value="${election.id}" />
                            <button class="inline-block px-6 py-2.5 bg-purple-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-purple-700 hover:shadow-lg focus:bg-purple-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-purple-800 active:shadow-lg transition duration-150 ease-in-out">View</button>
                        </form>

                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</t:layout>

<c:if test="${electionErrors != null}">
    <jsp:include page="WEB-INF/includes/components/toast.jsp">
        <jsp:param name="toastType" value="danger"></jsp:param>
        <jsp:param name="toastTitle" value="Cannot Create Election"></jsp:param>
        <jsp:param name="colour" value="bg-red-600"></jsp:param>
        <jsp:param name="toastBody" value="${electionErrors}"></jsp:param>
    </jsp:include>
</c:if>

<c:if test="${hasCreated != null}">
    <jsp:include page="WEB-INF/includes/components/toast.jsp">
        <jsp:param name="toastType" value="info"></jsp:param>
        <jsp:param name="toastTitle" value="Election Scheduled"></jsp:param>
        <jsp:param name="colour" value="bg-blue-600"></jsp:param>
        <jsp:param name="toastBody" value="Election created. Here's to a brighter future!"></jsp:param>
    </jsp:include>
</c:if>

<script src="js/toast.js"></script>