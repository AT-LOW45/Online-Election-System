
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="w-60 shadow-2xl bg-white sticky top-0 h-screen" style="padding: 0">

    <!-- User profile -->
    <div class="bg-gray-200 flex flex-col items-center" style="padding: 1.2rem">
        <i class="bi bi-person-circle block" style="font-size: 5rem"></i>
        <div class="text-2xl font-semibold">${sessionScope.username}</div>
        <div class="mt-4">Role: <span class="p-1 rounded bg-indigo-500 text-white">${sessionScope.role}</span></div>
        <a class="mt-6" href="ProfileController?profileAction=FIND_CURRENT_PROFILE">
            <button type="button" class="inline-block px-6 py-2.5 bg-purple-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-purple-700 hover:shadow-lg focus:bg-purple-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-purple-800 active:shadow-lg transition duration-150 ease-in-out">Edit Profile <i class="bi bi-pencil-fill"></i></button>
        </a>
        <form class="mt-2" method="POST" action="AuthController">
            <input type="hidden" value="LOGOUT" name="authAction"  />
            <button class="inline-block px-6 py-2.5 bg-yellow-500 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-yellow-600 hover:shadow-lg focus:bg-yellow-600 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-yellow-700 active:shadow-lg transition duration-150 ease-in-out">Logout <i class="bi bi-box-arrow-right"></i></button>
        </form>
    </div>

    <!-- nav links -->
    <ul class="relative">
        <c:if test="${sessionScope.role == 'COMMITTEE'}">
            <li class="relative">
                <a href="ProfileController?profileAction=FIND_ALL_PROFILE" class="flex items-center text-sm py-4 px-6 h-12 overflow-hidden text-gray-700 text-ellipsis whitespace-nowrap rounded hover:text-gray-900 hover:bg-gray-100 transition duration-300 ease-in-out" href="users.jsp"><i class="bi bi-people-fill self-center text-2xl mr-5"></i> Users</a>
            </li>
        </c:if>

        <li class="relative">
            <a href="ElectionController?electionAction=FIND_ELECTIONS" class="flex items-center text-sm py-4 px-6 h-12 overflow-hidden text-gray-700 text-ellipsis whitespace-nowrap rounded hover:text-gray-900 hover:bg-gray-100 transition duration-300 ease-in-out" href="#!" ><i class="bi bi-ticket-detailed self-center text-2xl mr-5"></i> Election</a>
        </li>
    </ul>
</div>
