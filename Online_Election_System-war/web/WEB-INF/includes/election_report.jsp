
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<section class="electionStatus">
    <c:choose>
        <c:when test="${selectedElection.status == true}">
            <h2 class="font-semibold text-2xl">Election Status: <span class="text-green-500">Ongoing</span></h2>
        </c:when>
        <c:otherwise>
            <h2 class="font-semibold text-2xl">Election Status: <span class="text-yellow-600">Concluded</span></h2>
        </c:otherwise>
    </c:choose>
    <h3 class="text-xl mt-3">Vote Rate: ${voteRate}%</h3>

    <c:if test="${selectedElection.status == false}">
        <div class="flex flex-col">
            <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
                <div class="py-4 inline-block min-w-full sm:px-6 lg:px-8">
                    <div class="overflow-hidden">
                        <table class="min-w-full text-center">
                            <thead class="border-b bg-gray-800">
                                <tr>
                                    <th scope="col" class="text-sm font-medium text-white px-6 py-4">
                                        Contester
                                    </th>
                                    <th scope="col" class="text-sm font-medium text-white px-6 py-4">
                                        Seat Won
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${winnerSeatMapping}" var="mapping">
                                    <tr class="bg-white border-b">
                                        <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                                            <c:choose>
                                                <c:when test="${mapping.key == null}">
                                                    No contester
                                                </c:when>
                                                <c:otherwise>
                                                    ${mapping.key.contesterProfile.tpNumber}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                                            ${mapping.value.seatName}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

</section>


<c:if test="${sessionScope.role == 'COMMITTEE'}">
    <section class="statistics flex sm:flex-col lg:flex-row">
        
        <!-- no. of votes for each contester -->
        <c:forEach items="${contesterVoteMapping}" var="mapping">
            <div class="contesterList hidden">
                <span class="contesterTp">${mapping.key.contesterProfile.tpNumber}</span>
                <span class="contesterVote">${mapping.value}</span>
            </div>
        </c:forEach>

        <div class="shadow-lg rounded-lg mt-7 overflow-hidden basis-1/2">
            <div class="py-3 px-5 bg-gray-50">No. of Votes for each Contester</div>
            <canvas class="p-10" id="contesterVotesBarChart"></canvas>
        </div>

        <!-- no. of contesters for each seat -->
        <c:forEach items="${seatContesterMapping}" var="mapping">
            <div class="seatList hidden">
                <span class="seatName">${mapping.key.seatName}</span>
                <span class="seatContesterNumber">${mapping.value}</span>
            </div>
        </c:forEach>

        <div class="shadow-lg rounded-lg mt-7 overflow-hidden basis-1/2">
            <div class="py-3 px-5 bg-gray-50">Number of Contesters for each Seat</div>
            <canvas class="p-10" id="seatContesterNumberDoughnut"></canvas>
        </div>
    </section>
</c:if>


