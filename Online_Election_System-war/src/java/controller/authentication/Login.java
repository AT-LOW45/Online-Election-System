package controller.authentication;

import controller.ControllerAction;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Profile;
import model.facade.ProfileFacade;

public class Login implements ControllerAction {

    private final ProfileFacade profileFacade = lookupProfileFacadeBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(request.getParameter("authAction"));
        Profile userProfile = profileFacade.authenticateUser(username, password);

        try {
            if (userProfile != null && userProfile.isActive() == true) {

                HttpSession userSession = request.getSession(false);
                userSession.setAttribute("userId", userProfile.getId());
                userSession.setAttribute("username", userProfile.getUsername());
                userSession.setAttribute("role", userProfile.getRole());
                userSession.setAttribute("approved", userProfile.isStatus());
                userSession.setAttribute("tpNumber", userProfile.getTpNumber());

                response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
                request.getRequestDispatcher("ElectionController?electionAction=FIND_ELECTIONS").include(request, response);

            } else {
                request.setAttribute("credentialError", "Invalid credentials. Please try again.");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        } catch (ServletException ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private ProfileFacade lookupProfileFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProfileFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/ProfileFacade!model.facade.ProfileFacade");

        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
