
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>

<jsp:include page="WEB-INF/includes/components/add_seat_modal.jsp"></jsp:include>
<jsp:include page="WEB-INF/includes/components/contest_seat_modal.jsp"></jsp:include>
<jsp:include page="WEB-INF/includes/components/vote_modal.jsp"></jsp:include>
<t:layout>
    <jsp:attribute name="header"></jsp:attribute>
    <jsp:attribute name="footer"></jsp:attribute>

    <jsp:body>
        <jsp:include page="WEB-INF/includes/components/hero.jsp">
            <jsp:param name="title" value="${selectedElection.title}" />
            <jsp:param name="fromColour" value="from-emerald-400" />
            <jsp:param name="toColour" value="to-emerald-100" />
        </jsp:include>

        <ul class="
            nav nav-tabs nav-justified
            flex flex-col
            md:flex-row
            flex-wrap
            list-none
            border-b-0
            pl-0
            mt-5
            mb-4
            " id="tabs-tabJustify" role="tablist">
            <c:if test="${selectedElection.status == true}">
                <li class="nav-item flex-grow text-center" role="presentation">
                    <a href="#tabs-homeJustify" class="
                       nav-link
                       w-full
                       block
                       font-medium
                       text-xs
                       leading-tight
                       uppercase
                       border-x-0 border-t-0 border-b-2 border-transparent
                       px-6
                       py-3
                       my-2
                       hover:border-transparent hover:bg-gray-100
                       focus:border-transparent
                       active
                       " id="tabs-home-tabJustify" data-bs-toggle="pill" data-bs-target="#tabs-homeJustify" role="tab"
                       aria-controls="tabs-homeJustify" aria-selected="true">Seats</a>
                </li>
            </c:if>

            <li class="nav-item flex-grow text-center" role="presentation">
                <a href="#tabs-profileJustify" class="
                   nav-link
                   w-full
                   block
                   font-medium
                   text-xs
                   leading-tight
                   uppercase
                   border-x-0 border-t-0 border-b-2 border-transparent
                   px-6
                   py-3
                   my-2
                   hover:border-transparent hover:bg-gray-100
                   focus:border-transparent
                   ${selectedElection.status == false ? 'active' : ''}
                   " id="tabs-profile-tabJustify" data-bs-toggle="pill" data-bs-target="#tabs-profileJustify" role="tab"
                   aria-controls="tabs-profileJustify" aria-selected="false">Report</a>
            </li>

        </ul>
        <div class="tab-content px-10 py-7" id="tabs-tabContentJustify">
            <c:if test="${selectedElection.status == true}">
                <div class="tab-pane fade show active" id="tabs-homeJustify" role="tabpanel"
                     aria-labelledby="tabs-home-tabJustify">
                    <c:if test="${sessionScope.approved == false && sessionScope.role != 'COMMITTEE'}">
                        <p class='mb-3'>Your registration has not been approved by the committee. Only approved users are allowed to vote/contest</p>
                    </c:if>

                    <c:if test="${loggedInContester.contested != null}">
                        <p class='mb-3'>You are currently contesting for <span class='font-bold'>${loggedInContester.contested.seatName}</span>. Please wait for the voting results to be announced.</p>
                    </c:if>

                    <c:if test="${loggedInVoter.election != null}">
                        <p class="mb-3">Your vote has been casted. Please wait for the election results to be announced by the committee.</p>
                    </c:if>
                    <c:choose>
                        <c:when test="${sessionScope.role == 'VOTER'}">
                            <c:choose>
                                <c:when test="${contesters.isEmpty()}">
                                    <p>No contesters</p>
                                </c:when>
                                <c:otherwise>
                                    <div class="flex flex-col space-y-4 voterView">
                                        <c:forEach items="${contesters}" var="contester">
                                            <div class="contesterCard flex justify-center" data-contesterId="${contester.id}" data-contesterTp="${contester.contesterProfile.tpNumber}">
                                                <div class="flex flex-col md:flex-row rounded-lg bg-white border border-stone-700 mr-auto" style="min-width: 60%">
                                                    <i class="bi bi-person-circle block px-4 border-r border-stone-400" style="font-size: 3.5rem"></i>
                                                    <div class="p-6 flex flex-col justify-start grow">
                                                        <h5 class="text-gray-900 text-xl font-medium mb-2">${contester.contesterProfile.username} - TP${contester.contesterProfile.tpNumber}</h5>
                                                        <div class="cardBody">
                                                            <p class="text-gray-700 text-base mb-4">
                                                                ${contester.contested.seatName}
                                                            </p>
                                                        </div>
                                                                <c:if test="${loggedInVoter.election == null && sessionScope.approved == true}">
                                                            <button data-bs-toggle="modal" data-bs-target="#voteModal" type="button" class="voteButton inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded-full shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">Vote</button>
                                                        </c:if>

                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>

                                </c:otherwise>
                            </c:choose>
                        </c:when>

                        <c:otherwise>
                            <c:if test="${sessionScope.role == 'COMMITTEE'}">
                                <form method="POST" action="ElectionController" class="mb-5">
                                    <input type="hidden" name="electionAction" value="CONCLUDE_ELECTION" />
                                    <input type="hidden" name="election_id" value="${selectedElection.id}" />
                                    <button class="inline-block px-6 py-2.5 bg-gray-800 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-gray-900 hover:shadow-lg focus:bg-gray-900 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-gray-900 active:shadow-lg transition duration-150 ease-in-out">conclude election</button>
                                </form>
                            </c:if>

                            <c:choose>
                                <c:when test="${selectedElection.seats.isEmpty()}">
                                    <p>No seats yet, please wait/create</p>
                                </c:when>
                                <c:otherwise>
                                    <div class="flex flex-col space-y-4">
                                        <c:forEach items="${selectedElection.seats}" var="seat">
                                            <div class="seatCard flex justify-center" data-seatId="${seat.id}" data-seatName="${seat.seatName}">
                                                <div class="flex flex-col md:flex-row  rounded-lg bg-white border border-stone-700 mr-auto" style="min-width: 60%">
                                                    <i style="font-size: 3.5rem;" class="block bi bi-bookmark-star-fill px-4 border-r border-stone-400"></i>
                                                    <div class="p-6 flex flex-col justify-start grow">
                                                        <h5 class="text-gray-900 text-xl font-medium mb-2">${seat.seatName}</h5>
                                                        <div class="cardBody">
                                                            <c:choose>
                                                                <c:when test="${seat.contesters.isEmpty()}">
                                                                    <p>No contesters yet</p>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <p>Contesters: ${seat.contesters.size()}/${seat.contesterLimit}</p>
                                                                    <div class="flex w-full flex-wrap space-x-2 my-4">
                                                                        <c:forEach items="${seat.contesters}" var="contester">
                                                                            <span
                                                                                class="px-4 py-2 rounded-full text-gray-500 bg-gray-200 font-semibold text-sm flex align-center w-max cursor-pointer active:bg-gray-300 transition duration-300 ease">
                                                                                TP${contester.contesterProfile.tpNumber}
                                                                            </span>
                                                                        </c:forEach>
                                                                    </div>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <c:if test="${seat.contesters.size() == seat.contesterLimit}">
                                                                <p class="text-red-500 mt-3">This seat has reached its contester limit.</p>
                                                            </c:if>

                                                                <c:if test="${seat.contesters.size() < seat.contesterLimit && sessionScope.role == 'CONTESTER' && loggedInContester.contested == null && sessionScope.approved == true}">
                                                                <button data-bs-toggle="modal" data-bs-target="#contestSeatModal" type="button" class="contestButton mt-3 block ml-auto px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded-full shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">Contest</button>  
                                                            </c:if>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>

                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>

                    </c:choose>
                </div>
            </c:if>


            <div class="tab-pane fade ${selectedElection.status == false ? 'active show' : ''}" id="tabs-profileJustify" role="tabpanel" aria-labelledby="tabs-profile-tabJustify">
                <jsp:include page="WEB-INF/includes/election_report.jsp"></jsp:include>
                </div>
            </div>

        <c:if test="${sessionScope.role == 'COMMITTEE' && selectedElection.status == true}">
            <button style="width: 4rem; height: 4rem; right: 8%; bottom: 7%" data-bs-toggle="modal" data-bs-target="#addSeatModal" class="fixed pointer rounded-full bg-blue-600 text-white leading-normal uppercase shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out w-9 h-9">
                <i style="font-size: 2rem;" class="bi bi-bookmark-plus-fill"></i>
            </button>
        </c:if>
    </jsp:body>
</t:layout>

<script src="js/election_details_page.js"></script>
