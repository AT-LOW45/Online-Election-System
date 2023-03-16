
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div style="height: 25vh;" class="hero relative bg-gradient-to-r translate-y-[-30%] ${param.fromColour} ${param.toColour}">
    <h1 class="text-3xl pl-10 pt-7 ${param.titleColour}">${param.title}</h1>
    <c:choose>
        <c:when test="${param.type == 'users'}">
            <div class="absolute flex items-center justify-center mb-3" style="left: 10%; top: 87%">
                <div class="inline-flex shadow-md hover:shadow-lg focus:shadow-lg" role="group">
                    <button data-te-toggle="modal" data-te-target="#addCommitteeModal" type="button" class="rounded-l inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase hover:bg-blue-700 focus:bg-blue-700 focus:outline-none focus:ring-0 active:bg-blue-800 transition duration-150 ease-in-out">Add Committee Member <i class="ml-3 font-bold bi bi-plus-lg"></i></button>
                    <form method="POST" action="ProfileController?profileAction=FIND_ALL_PROFILE">
                        <button class=" rounded-r inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase hover:bg-blue-700 focus:bg-blue-700 focus:outline-none focus:ring-0 active:bg-blue-800 transition duration-150 ease-in-out">restore list</button>
                    </form>
                </div>
            </div>
        </c:when>
        <c:when test="${param.type == 'election' && sessionScope.role == 'COMMITTEE'}">
            <button   data-te-toggle="modal" data-te-target="#scheduleElectionModal" type="button" style="left: 10%; top: 87%" class="rounded absolute inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase hover:bg-blue-700 focus:bg-blue-700 focus:outline-none focus:ring-0 active:bg-blue-800 transition duration-150 ease-in-out">Schedule Election <i class="ml-3 font-bold bi bi-plus-lg"></i></button>
            </c:when>
    </c:choose>
</div>
