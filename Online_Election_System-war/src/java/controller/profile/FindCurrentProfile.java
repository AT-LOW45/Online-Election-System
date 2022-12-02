package controller.profile;

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
import model.Profile;
import model.facade.ProfileFacade;

public class FindCurrentProfile implements ControllerAction {

    private final ProfileFacade profileFacade = lookupProfileFacadeBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Profile currentUser = profileFacade.find(request.getSession(false).getAttribute("userId"));
        request.setAttribute("currentUser", currentUser);

        try {
            request.getRequestDispatcher("profile.jsp").include(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(FindCurrentProfile.class.getName()).log(Level.SEVERE, null, ex);
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
