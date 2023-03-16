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
import model.Seat;
import model.facade.ElectionFacade;
import model.facade.SeatFacade;

public class AddNewSeat implements ControllerAction {

    private final SeatFacade seatFacade = lookupSeatFacadeBean();
    private final ElectionFacade electionFacade = lookupElectionFacadeBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String seatName = request.getParameter("seatName");
        int contesterLimit = Integer.parseInt(request.getParameter("contesterLimit"));

        Election selectedElection = electionFacade.find(Long.parseLong(request.getParameter("election_id")));
        Seat newSeat = new Seat(seatName, contesterLimit, selectedElection);
        seatFacade.create(newSeat);
        selectedElection.getSeats().add(newSeat);
        electionFacade.edit(selectedElection);

        try {
            request.getRequestDispatcher("ElectionController?electionAction=FIND_SELECTED_ELECTION")
                    .forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddNewSeat.class.getName()).log(Level.SEVERE, null, ex);
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

    private SeatFacade lookupSeatFacadeBean() {
        try {
            Context c = new InitialContext();
            return (SeatFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/SeatFacade!model.facade.SeatFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
