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
import model.Contester;
import model.Contester_Seat;
import model.Election;
import model.Seat;
import model.facade.ContesterFacade;
import model.facade.Contester_SeatFacade;
import model.facade.ElectionFacade;
import model.facade.SeatFacade;

public class ContestSeat implements ControllerAction {

    private final Contester_SeatFacade contester_SeatFacade = lookupContester_SeatFacadeBean();
    private final ElectionFacade electionFacade = lookupElectionFacadeBean();
    private final ContesterFacade contesterFacade = lookupContesterFacadeBean();
    private final SeatFacade seatFacade = lookupSeatFacadeBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Seat seat = seatFacade.find(Long.parseLong(request.getParameter("seat_id")));
        Contester contester = contesterFacade.findByProfileId((Long) request.getSession(false).getAttribute("userId"));

        Election election = electionFacade.find(Long.parseLong(request.getParameter("election_id")));

        contester.setContested(seat);
        seat.getContesters().add(contester);

        contester_SeatFacade.create(new Contester_Seat(contester, seat, election, 0));
        contesterFacade.edit(contester);
        seatFacade.edit(seat);

        try {
            request.getRequestDispatcher("ElectionController?electionAction=FIND_SELECTED_ELECTION")
                    .forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ContestSeat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private SeatFacade lookupSeatFacadeBean() {
        try {
            Context c = new InitialContext();
            return (SeatFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/SeatFacade!model.facade.SeatFacade");
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

    private ElectionFacade lookupElectionFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ElectionFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/ElectionFacade!model.facade.ElectionFacade");
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
