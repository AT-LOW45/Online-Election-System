/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.election;

/**
 *
 * @author Koh
 */
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
import model.Contester_Seat;
import model.Election;
import model.Voter;
import model.Voter_Contester;
import model.facade.ContesterFacade;
import model.facade.Contester_SeatFacade;
import model.facade.ElectionFacade;
import model.facade.VoterFacade;
import model.facade.Voter_ContesterFacade;

public class Vote implements ControllerAction {

    Contester_SeatFacade contester_SeatFacade = lookupContester_SeatFacadeBean();

    Voter_ContesterFacade voter_ContesterFacade = lookupVoter_ContesterFacadeBean();

    VoterFacade voterFacade = lookupVoterFacadeBean();

    ElectionFacade electionFacade = lookupElectionFacadeBean();

    ContesterFacade contesterFacade = lookupContesterFacadeBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Contester contester = contesterFacade.find(Long.parseLong(request.getParameter("contester_id")));
        contester.setVotes(contester.getVotes() + 1); // TODO - remove
        contesterFacade.edit(contester);
        Contester_Seat conSeat = contester_SeatFacade.findByContesterId(contester.getId());

        conSeat.setVotes(conSeat.getVotes() + 1);

        Voter voter = voterFacade.findByProfileId((Long) request.getSession(false).getAttribute("userId"));

        Election election = electionFacade.find(Long.parseLong(request.getParameter("election_id")));

        voter.setElection(election);
        election.getVoters().add(voter);

        voter_ContesterFacade.create(new Voter_Contester(contester, voter, election));
        contester_SeatFacade.edit(conSeat);
        voterFacade.edit(voter);
        electionFacade.edit(election);

        try {
            request.getRequestDispatcher("ElectionController?electionAction=FIND_SELECTED_ELECTION").include(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Vote.class.getName()).log(Level.SEVERE, null, ex);
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

    private ElectionFacade lookupElectionFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ElectionFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/ElectionFacade!model.facade.ElectionFacade");
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

    private Voter_ContesterFacade lookupVoter_ContesterFacadeBean() {
        try {
            Context c = new InitialContext();
            return (Voter_ContesterFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/Voter_ContesterFacade!model.facade.Voter_ContesterFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private Contester_SeatFacade lookupContester_SeatFacadeBean() {
        try {
            Context c = new InitialContext();
            return (Contester_SeatFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/Contester_SeatFacade!model.facade.Contester_SeatFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
