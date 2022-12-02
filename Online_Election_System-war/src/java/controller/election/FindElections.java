package controller.election;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.facade.ElectionFacade;
import controller.ControllerAction;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindElections implements ControllerAction {

    private final ElectionFacade electionFacade = lookupElectionFacadeBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("ongoingElection", electionFacade.findOngoingElection());
        request.setAttribute("finishedElections", electionFacade.findFinishedElections());

        try {
            request.getRequestDispatcher("election.jsp").include(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(FindElections.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ElectionFacade lookupElectionFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ElectionFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/ElectionFacade!model.facade.ElectionFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
