<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<t:minimal_layout>
    <jsp:body>
        <section class="h-screen">
            <div style="padding: 0 6rem 0 6rem;" class="h-full text-gray-800">
                <div
                    class="flex xl:justify-center lg:justify-between justify-center items-center flex-wrap h-full g-6"
                    >
                    <div
                        class="grow-0 shrink-1 md:shrink-0 basis-auto xl:w-6/12 lg:w-6/12 md:w-9/12 mb-12 md:mb-0"
                        >
                        <img
                            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                            class="w-full"
                            alt="Sample image"
                            />
                    </div>
                    <div class="xl:ml-20 xl:w-5/12 lg:w-5/12 md:w-8/12 mb-12 md:mb-0">
                        <form method="POST" action="AuthController">
                            <input type="hidden" value="LOGIN" name="authAction" />
                            <div
                                class="flex items-center my-4 before:flex-1 before:border-t before:border-gray-300 before:mt-0.5 after:flex-1 after:border-t after:border-gray-300 after:mt-0.5"
                                >
                                <p class="text-center font-semibold mx-4 mb-0">APU SRC Online Election System</p>
                            </div>

                            <div class="mb-6">
                                <input
                                    name="username"
                                    type="text"
                                    class="form-control block w-full px-4 py-2 text-xl font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                                    id="exampleFormControlInput2"
                                    placeholder="Username"
                                    />
                            </div>

                            <!-- Password input -->
                            <div class="mb-6">
                                <input
                                    name="password"
                                    type="password"
                                    class="form-control block w-full px-4 py-2 text-xl font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                                    id="exampleFormControlInput2"
                                    placeholder="Password"
                                    />
                            </div>
                            
                            <c:if test="${credentialError != null}">
                                <p class="mt-4 text-red-500">${credentialError}</p>
                            </c:if>
                                
                            <p class="mt-4"></p>

                            <div class="text-center lg:text-left">
                                <button
                                    class="inline-block px-7 py-3 bg-blue-600 text-white font-medium text-sm leading-snug uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out"
                                    >
                                    Login
                                </button>
                                <p class="text-sm font-semibold mt-2 pt-1 mb-0">
                                    Don't have an account?
                                    <a
                                        href="register.jsp"
                                        class="text-red-600 hover:text-red-700 focus:text-red-700 transition duration-200 ease-in-out"
                                        >Register</a
                                    >
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </jsp:body>
</t:minimal_layout>