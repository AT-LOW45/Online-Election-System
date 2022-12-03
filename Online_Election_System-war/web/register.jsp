<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<t:minimal_layout>
    <jsp:body>
        <div class="flex h-screen items-center justify-center min-h-screen bg-gray-100">
            <div style="padding: 2rem;" class="rounded mx-4 mt-4 text-left bg-white shadow-lg md:w-1/3 lg:w-1/3 sm:w-1/3">
                <div class="flex justify-center">
                    <svg xmlns="http://www.w3.org/2000/svg" class="w-20 h-20 text-blue-600" fill="none" viewBox="0 0 24 24"
                         stroke="currentColor">
                        <path d="M12 14l9-5-9-5-9 5 9 5z" />
                        <path
                            d="M12 14l6.16-3.422a12.083 12.083 0 01.665 6.479A11.952 11.952 0 0012 20.055a11.952 11.952 0 00-6.824-2.998 12.078 12.078 0 01.665-6.479L12 14z" />
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                              d="M12 14l9-5-9-5-9 5 9 5zm0 0l6.16-3.422a12.083 12.083 0 01.665 6.479A11.952 11.952 0 0012 20.055a11.952 11.952 0 00-6.824-2.998 12.078 12.078 0 01.665-6.479L12 14zm-4 6v-7.5l4-2.222" />
                    </svg>
                </div>
                <h3 class="text-2xl font-bold text-center">Join us</h3>
                <form action="AuthController" method="POST">
                    <div class="mt-4">
                        
                        <div>
                            <label class="block" for="username">Name<label>
                            <input type="text" placeholder="Username" name="username" required class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
                        </div>
                        
                        <div class="mt-4">
                            <label class="block" for="password">Password<label>
                            <input type="password" placeholder="Password" name="password" required class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
                        </div>
                        <c:if test="${errorList != null}">
                            <p class="text-sm text-red-500">${errorList.get("password")}</p>
                        </c:if>
                        
                        
                        <div class="mt-4">
                            <label class="block" for="confirmPassword">Confirm Password<label>
                            <input type="password" placeholder="Password" name="confirmPassword" required class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
                        </div>
                        <c:if test="${errorList != null}">
                            <p class="text-sm text-red-500">${errorList.get("confirmPassword")}</p>
                        </c:if>
                            
                        <div class="mt-4">
                            <label class="block" for="tpNumber">TP Number</label>
                            <input type="number" required placeholder="TP Number" name="tpNumber" class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
                        </div>
                        <c:if test="${errorList != null}">
                            <p class="text-sm text-red-500">${errorList.get("tpNumber")}</p>
                        </c:if>
                            
                        <div class="mt-4">
                            <label class="block" for="programme">Programme</label>
                            <input type="text" required placeholder="Programme" name="programme" class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-600">
                        </div>
                        
                        <div class="mt-4">
                           <div class="flex justify-center">
                                <div class="mb-3 xl:w-96">
                                    <label class="block">Register as:<label>
                                    <select class="form-select appearance-none
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
                                        focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" aria-label="Default select example" name="role">
                                        
                                        <option value="CONTESTER" selected>Contester</option>
                                        <option value="VOTER">Voter</option>
                                        <option value="COMMITTEE">Committee</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                            <input type="hidden" name="authAction" value="REGISTER" />
                        
                        <div class="flex">
                            <button class="w-full px-6 py-2 mt-4 text-white bg-blue-600 rounded-lg hover:bg-blue-900">Create Account</button>
                        </div>
                        
                        <div class="mt-6 text-grey-dark">Already have an account? <a class="text-blue-600 hover:underline" href="login.jsp">Log in</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:minimal_layout>

<c:if test="${errorList != null}">
    <jsp:include page="WEB-INF/includes/components/toast.jsp">
        <jsp:param name="toastType" value="danger"></jsp:param>
        <jsp:param name="toastTitle" value="Invalid fields"></jsp:param>
        <jsp:param name="colour" value="bg-red-600"></jsp:param>
        <jsp:param name="toastBody" value="Some fields are invalid, please check again"></jsp:param>
    </jsp:include>
</c:if>
<script src="js/toast.js"></script>