
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="addCommitteeModal" tabindex="-1" aria-labelledby="exampleModalCenterTitle" aria-modal="true" role="dialog">
    <div class="modal-dialog modal-dialog-centered relative w-auto pointer-events-none">
        <form method="POST" action="AuthController" id="addCommitteeForm" class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white bg-clip-padding rounded-md outline-none text-current">
            <div class="modal-header flex flex-shrink-0 items-center justify-between p-4 border-b border-gray-200 rounded-t-md">
                <h5 class="text-xl font-medium leading-normal text-gray-800" id="exampleModalScrollableLabel">
                    Add Committee Member
                </h5>
                <button type="button"
                        class="btn-close box-content w-4 h-4 p-1 text-black border-none rounded-none opacity-50 focus:shadow-none focus:outline-none focus:opacity-100 hover:text-black hover:opacity-75 hover:no-underline"
                        data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body relative p-4">
                <div >
                    <div class="form-group mb-6">
                        <label for="username" class="form-label inline-block mb-2 text-gray-700">Username</label>
                        <input type="text" name='username' required class="form-control
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
                               focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                               placeholder="Username">
                    </div>
                    <div class="form-group mb-6">
                        <label for="password" class="form-label inline-block mb-2 text-gray-700">Password</label>
                        <input type="password" name="password" required class="form-control block
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
                               focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                               placeholder="Password">
                    </div>

                    <div class="form-group mb-6">
                        <label for="confirmPassword" class="form-label inline-block mb-2 text-gray-700">Password</label>
                        <input type="password" name="confirmPassword" required class="form-control block
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
                               focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                               placeholder="Confirm password">
                    </div>

                    <div class="form-group mb-6">
                        <label for="tpNumber" class="form-label inline-block mb-2 text-gray-700">TP Number</label>
                        <input type="text" name="tpNumber" required class="form-control block
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
                               focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                               placeholder="TP Number">
                    </div>
                    <div class="form-group mb-6">
                        <label for="programme" class="form-label inline-block mb-2 text-gray-700">Programme</label>
                        <input type="text" name="programme" required class="form-control block
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
                               focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                               placeholder="Programme">
                    </div>
                </div>
            </div>
            <div 
                class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end p-4 border-t border-gray-200 rounded-b-md">
                <input type="hidden" value="COMMITTEE" name="role" />
                <input type="hidden" value="REGISTER" name="authAction" />
                <button type="button"
                        class="inline-block px-6 py-2.5 bg-purple-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-purple-700 hover:shadow-lg focus:bg-purple-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-purple-800 active:shadow-lg transition duration-150 ease-in-out"
                        data-bs-dismiss="modal">
                    Cancel
                </button>
                <button
                    class="inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out ml-1">
                    Add
                </button>
            </div>
        </form>
    </div>
</div>
