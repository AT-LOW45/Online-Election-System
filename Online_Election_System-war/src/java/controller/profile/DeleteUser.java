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
import model.Contester;
import model.Profile;
import model.Voter;
import model.facade.ContesterFacade;
import model.facade.ProfileFacade;
import model.facade.VoterFacade;
import role.Role;

public class DeleteUser implements ControllerAction {

    private final ProfileFacade profileFacade = lookupProfileFacadeBean1();
    private final ContesterFacade contesterFacade = lookupContesterFacadeBean();
    private final VoterFacade voterFacade = lookupVoterFacadeBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        
        Profile profile = profileFacade.find(Long.parseLong(request.getParameter("user_id")));
        profile.setActive(false);
        profileFacade.edit(profile);

//        switch (Role.valueOf(request.getParameter("role"))) {
//            case CONTESTER:
//                Contester contester = contesterFacade.findByProfileId(Long.parseLong(request.getParameter("user_id")));
//                contesterFacade.remove(contester);
//                break;
//            case VOTER:
//                Voter voter = voterFacade.findByProfileId(Long.parseLong(request.getParameter("user_id")));
//                voterFacade.remove(voter);
//                break;
//            default:
//                Profile profile = profileFacade.find(Long.parseLong(request.getParameter("user_id")));
//                profileFacade.remove(profile);
//                break;
//        }

        request.setAttribute("deletedUserId", request.getParameter("user_id"));
        request.setAttribute("allProfiles", profileFacade.findActiveProfiles());
        try {
            request.getRequestDispatcher("ProfileController?profileAction=FIND_ALL_PROFILE")
                    .include(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(DeleteUser.class.getName()).log(Level.SEVERE, null, ex);
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

    private VoterFacade lookupVoterFacadeBean() {
        try {
            Context c = new InitialContext();
            return (VoterFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/VoterFacade!model.facade.VoterFacade");
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

    private ProfileFacade lookupProfileFacadeBean1() {
        try {
            Context c = new InitialContext();
            return (ProfileFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/ProfileFacade!model.facade.ProfileFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
