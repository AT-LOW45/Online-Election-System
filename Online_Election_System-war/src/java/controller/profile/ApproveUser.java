package controller.profile;

import controller.ControllerAction;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Profile;
import model.facade.ContesterFacade;
import model.facade.ProfileFacade;

public class ApproveUser implements ControllerAction {

    private final ContesterFacade contesterFacade = lookupContesterFacadeBean();
    private final ProfileFacade profileFacade = lookupProfileFacadeBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Profile profile = profileFacade.find(Long.parseLong(request.getParameter("user_id")));
        profile.setStatus(true);
        profileFacade.edit(profile);

        try {
//            List<Profile> allProfiles = profileFacade.findAll();
//            request.setAttribute("allProfiles", allProfiles);
            request.setAttribute("approvedUserTp", profile.getTpNumber());
            request.getRequestDispatcher("ProfileController?profileAction=FIND_ALL_PROFILE")
                    .include(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ApproveUser.class.getName()).log(Level.SEVERE, null, ex);
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

    private ContesterFacade lookupContesterFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ContesterFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/ContesterFacade!model.facade.ContesterFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
