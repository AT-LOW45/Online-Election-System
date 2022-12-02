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
import model.facade.ProfileFacade;
import role.Role;

public class FindByRoleAndStatus implements ControllerAction {

    private final ProfileFacade profileFacade = lookupProfileFacadeBean();

    public FindByRoleAndStatus() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        boolean status = request.getParameter("status").equals("1");
        Role role = Role.valueOf(request.getParameter("role"));

        request.setAttribute("allProfiles", profileFacade.findStatusAndRole(status, role));

        try {
            request.getRequestDispatcher("users.jsp").include(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(FindByRoleAndStatus.class.getName()).log(Level.SEVERE, null, ex);
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
