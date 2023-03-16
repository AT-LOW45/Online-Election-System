<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="WEB-INF/includes/components/approve_user_modal.jsp"></jsp:include>
<jsp:include page="WEB-INF/includes/components/delete_user_modal.jsp"></jsp:include>
<jsp:include page="WEB-INF/includes/components/update_user_modal.jsp"></jsp:include>
<jsp:include page="WEB-INF/includes/components/add_committee_modal.jsp"></jsp:include>

<t:layout>
    <jsp:attribute name="header"></jsp:attribute>
    <jsp:attribute name="footer"></jsp:attribute>

    <jsp:body>
        <jsp:include page="WEB-INF/includes/components/hero.jsp">
            <jsp:param name="title" value="Administration" />
            <jsp:param name="fromColour" value="from-amber-400" />
            <jsp:param name="toColour" value="to-amber-100" />
            <jsp:param name="type" value="users" /> 
        </jsp:include>

        <div class="flex justify-between px-4">

            <form class="flex items-center" action="ProfileController" method="POST">
                <label class="mr-3">Status: </label>
                <div class="flex justify-center">

                    <div class="xl:w-48 mr-4">
                        <select name="status" class="form-select appearance-none 
                                block
                                w-full
                                px-3
                                py-1.5
                                text-base
                                font-normal
                                text-gray-700
                                bg-white bg-clip-padding bg-no-repeat
                                border border-solid border-gray-300
                                rounded
                                transition
                                ease-in-out
                                m-0
                                focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="Default select example">
                            <option value="1">Approved</option>
                            <option value="0">Pending</option>
                        </select>
                    </div>
                </div>

                <label class="mr-3">Role: </label>
                <div class="flex justify-center">
                    <div class="xl:w-48 mr-4">
                        <select name="role" class="form-select appearance-none
                                block
                                w-full
                                px-3
                                py-1.5
                                text-base
                                font-normal
                                text-gray-700
                                bg-white bg-clip-padding bg-no-repeat
                                border border-solid border-gray-300
                                rounded
                                transition
                                ease-in-out
                                m-0
                                focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="Default select example">
                            <option value="CONTESTER">Contester</option>
                            <option value="COMMITTEE">Committee</option>
                            <option value="VOTER">Voter</option>
                        </select>
                    </div>
                </div>

                <input type="hidden" value="FIND_BY_ROLE_AND_STATUS" name="profileAction" />
                <button class="inline-block px-6 py-2.5 bg-gray-200 text-gray-700 font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-gray-300 hover:shadow-lg focus:bg-gray-300 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-gray-400 active:shadow-lg transition duration-150 ease-in-out">Apply Filters</button>
            </form>

            <div class="flex items-center">
                <label class="mr-3">Sort by: </label>
                <div class="flex justify-center">
                    <form class="flex" style="width: 15rem;" action="ProfileController" method="POST">
                        <input type="hidden" name="profileAction" value="SORT_BY_CRITERIA" />
                        <select name="sortType" onchange="this.form.submit()" class="form-select appearance-none
                                block
                                w-full
                                px-3
                                py-1.5
                                text-base
                                font-normal
                                text-gray-700
                                bg-white bg-clip-padding bg-no-repeat
                                border border-solid border-gray-300
                                rounded
                                transition
                                ease-in-out
                                m-0
                                focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="Default select example">
                            <option value="earliest">Earliest joined</option>
                            <option value="latest">Latest joined</option>
                            <option value="status">Verification</option>
                        </select>
                    </form>
                </div>

            </div>
        </div>

        <div class="w-fit toggleDrop mr-auto mt-5 ml-4 cursor-pointer rounded relative border-2 py-1 px-2 rounded border-blue-700 bg-white hover:bg-blue-700 text-blue hover:text-white duration-300">Search By TP <i class="bi bi-tag-fill text-2xl"></i>

            <form method="POST" action="ProfileController?profileAction=FIND_BY_TP" style="visibility: hidden; opacity: 0; left: 105%; top: -50%" class="transition-all duration-300 tpForm absolute shadow-2xl py-3 px-4 rounded bg-white w-52 ">
                <input
                    name="tpNumber"
                    type="text"
                    class="
                    form-control
                    block
                    w-full
                    px-3
                    py-1.5
                    text-base
                    font-normal
                    text-gray-700
                    bg-white bg-clip-padding
                    border border-solid border-gray-300
                    rounded
                    transition
                    ease-in-out
                    m-0
                    focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none
                    "
                    id="exampleFormControlInput1"
                    placeholder="eg. 123456"/>
                <button class="mt-4 mx-auto inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">Find</button>
            </form>
        </div>

        <div class="flex flex-col mt-5">
            <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
                <div class="py-4 inline-block min-w-full sm:px-6 lg:px-8">
                    <div class="overflow-hidden">
                        <table class="min-w-full text-center">
                            <thead class="border-b bg-gray-800">
                                <tr>
                                    <th scope="col" class="text-sm font-medium text-white px-6 py-4">
                                        Date Joined
                                    </th>
                                    <th scope="col" class="text-sm font-medium text-white px-6 py-4">
                                        Username
                                    </th>
                                    <th scope="col" class="text-sm font-medium text-white px-6 py-4">
                                        TP Number
                                    </th>
                                    <th scope="col" class="text-sm font-medium text-white px-6 py-4">
                                        Status
                                    </th>
                                    <th scope="col" class="text-sm font-medium text-white px-6 py-4">
                                        Role
                                    </th>
                                    <th scope="col" class="text-sm font-medium text-white px-6 py-4">
                                        Programme
                                    </th>
                                    <th scope="col" class="text-sm font-medium text-white px-6 py-4">
                                        Action
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${allProfiles.isEmpty()}">
                                        <tr>
                                            <td>Wow, such empty.</td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${allProfiles}" var="profile">
                                            <tr class="bg-white border-b">
                                                <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${profile.dateJoined}</td>
                                                <td class="text-sm text-gray-900  px-6 py-4 whitespace-nowrap">
                                                    ${profile.username}
                                                </td>
                                                <td class="text-sm text-gray-900  px-6 py-4 whitespace-nowrap">
                                                    ${profile.tpNumber}
                                                </td>
                                                <td class="text-sm text-gray-900  px-6 py-4 whitespace-nowrap">
                                                    <c:choose>
                                                        <c:when test="${profile.status == true}">
                                                            <span class="py-1 px-2 bg-green-700 rounded text-white">Approved</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="py-1 px-2 bg-yellow-700 rounded text-white">Pending</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="text-sm text-gray-900  px-6 py-4 whitespace-nowrap">
                                                    ${profile.role}
                                                </td>
                                                <td class="text-sm text-gray-900  px-6 py-4 whitespace-nowrap">
                                                    ${profile.programme}
                                                </td>
                                                <td class="text-sm text-gray-900 px-6 py-4 whitespace-nowrap">
                                                    <div class="flex justify-center items-center">
                                                        <input type="hidden" name="user_id" value="${profile.id}" />
                                                        <input type="hidden" name="tpNumber" value="${profile.tpNumber}" />
                                                        <input type="hidden" name="role" value="${profile.role}" />

                                                        <c:if test="${profile.status == false}">

                                                            <button   data-te-toggle="modal"
                                                                      data-te-target="#approveUserModal" type="button" class="approveUserButton inline-block mr-3 px-6 py-2.5 bg-purple-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-purple-700 hover:shadow-lg focus:bg-purple-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-purple-800 active:shadow-lg transition duration-150 ease-in-out">Approve</button>

                                                        </c:if>
                                                        <button style="background-color: rgb(234 179 8);" type="button"   data-te-toggle="modal"
                                                                data-te-target="#updateUserModal" class="updateUserButton mr-3 inline-block px-6 py-2.5 bg-yellow-500 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-yellow-600 hover:shadow-lg focus:bg-yellow-600 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-yellow-700 active:shadow-lg transition duration-150 ease-in-out">Update</button>
                                                        <button type="button" data-te-toggle="modal" data-te-target="#deleteUserModal" class="deleteUserButton inline-block px-6 py-2.5 bg-red-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-red-700 hover:shadow-lg focus:bg-red-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-red-800 active:shadow-lg transition duration-150 ease-in-out">Delete</button>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>

<c:if test="${updateErrorList != null}">
    <jsp:include page="WEB-INF/includes/components/toast.jsp">
        <jsp:param name="toastType" value="danger"></jsp:param>
        <jsp:param name="toastTitle" value="Invalid fields"></jsp:param>
        <jsp:param name="colour" value="bg-red-600"></jsp:param>
        <jsp:param name="toastBody" value="${updateErrorList}"></jsp:param>
    </jsp:include>
</c:if>

<c:if test="${deletedUserId != null}">
    <jsp:include page="WEB-INF/includes/components/toast.jsp">
        <jsp:param name="toastType" value="info"></jsp:param>
        <jsp:param name="toastTitle" value="User Removed"></jsp:param>
        <jsp:param name="colour" value="bg-blue-600"></jsp:param>
        <jsp:param name="toastBody" value="You have deleted user ${deletedUserId}"></jsp:param>
    </jsp:include>
</c:if>

<c:if test="${approvedUserTp != null}">
    <jsp:include page="WEB-INF/includes/components/toast.jsp">
        <jsp:param name="toastType" value="info"></jsp:param>
        <jsp:param name="toastTitle" value="User approved"></jsp:param>
        <jsp:param name="colour" value="bg-blue-600"></jsp:param>
        <jsp:param name="toastBody" value="You have approved the registration of TP${approvedUserTp}"></jsp:param>
    </jsp:include>
</c:if>

<c:if test="${updatedUserTp != null}">
    <jsp:include page="WEB-INF/includes/components/toast.jsp">
        <jsp:param name="toastType" value="success"></jsp:param>
        <jsp:param name="toastTitle" value="User info updated"></jsp:param>
        <jsp:param name="colour" value="bg-green-600"></jsp:param>
        <jsp:param name="toastBody" value="User TP${updatedUserTp}'s info successfully updated"></jsp:param>
    </jsp:include>
</c:if>

<script src="js/dropToggle.js"></script>
<script src="js/users_page.js"></script>
<script src="js/toast.js"></script>