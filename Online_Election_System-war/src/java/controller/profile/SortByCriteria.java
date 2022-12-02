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

public class SortByCriteria implements ControllerAction {

    private final ProfileFacade profileFacade = lookupProfileFacadeBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String sortType = request.getParameter("sortType");
        System.out.println(sortType);

        switch (sortType) {
            case "status":
                request.setAttribute("allProfiles", profileFacade.sortByStatus());
                break;
            case "earliest":
                request.setAttribute("allProfiles", profileFacade.sortByEarliestJoined());
                break;
            case "latest":
                request.setAttribute("allProfiles", profileFacade.sortByLatestJoined());
                break;
            default:
                break;
        }


        try {
            request.getRequestDispatcher("users.jsp").include(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(SortByCriteria.class.getName()).log(Level.SEVERE, null, ex);
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
