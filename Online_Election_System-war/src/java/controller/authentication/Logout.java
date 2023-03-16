/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import controller.ControllerAction;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements ControllerAction {

    public Logout() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {

            request.getRequestDispatcher("login.jsp").include(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
