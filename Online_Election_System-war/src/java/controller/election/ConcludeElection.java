package controller.election;

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
import model.Election;
import model.facade.ContesterFacade;
import model.facade.ElectionFacade;
import model.facade.VoterFacade;

public class ConcludeElection implements ControllerAction {

    private final VoterFacade voterFacade = lookupVoterFacadeBean();
    private final ContesterFacade contesterFacade = lookupContesterFacadeBean();
    private final ElectionFacade electionFacade = lookupElectionFacadeBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Election election = electionFacade.find(Long.parseLong(request.getParameter("election_id")));

        election.setStatus(false);

        contesterFacade.findAll().forEach(contester -> {
            contester.setContested(null);
            contester.setVotes(0);
            contesterFacade.edit(contester);
        });

        voterFacade.findAll().forEach(voter -> {
            voter.setElection(null);
            voterFacade.edit(voter);
        });

        electionFacade.edit(election);

        try {
            request.getRequestDispatcher("ElectionController?electionAction=FIND_ELECTIONS").include(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ConcludeElection.class.getName()).log(Level.SEVERE, null, ex);
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

    private ContesterFacade lookupContesterFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ContesterFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/ContesterFacade!model.facade.ContesterFacade");
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

}
