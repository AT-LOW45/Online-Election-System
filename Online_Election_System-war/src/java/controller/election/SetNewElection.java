package controller.election;

import controller.ControllerAction;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Election;
import model.facade.ElectionFacade;
import utility.Validator;

public class SetNewElection implements ControllerAction {

    private final ElectionFacade electionFacade = lookupElectionFacadeBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("electionTitle");

        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Election newElection = new Election(title, startDate, endDate);

        Validator<Election> electionValidator = new Validator<>(newElection);

        boolean results = electionValidator
                .validate(elec -> elec.getEndDateUnformatted().isAfter(LocalDate.now()),
                        "startDate",
                        "The start date cannot be before today")
                .validate(elec -> elec.getEndDateUnformatted().isAfter(elec.getStartDateUnformatted()),
                        "endDate",
                        "End date cannot be before start date")
                .validate(elec -> electionFacade.findOngoingElection() == null,
                        "ongoingElection",
                        "There is an ongoing election at the moment")
                .getValidationResult();

        if (results == true) {
            electionFacade.create(newElection);
            request.setAttribute("hasCreated", true);
            try {
                request.getRequestDispatcher("ElectionController?electionAction=FIND_ELECTIONS")
                        .include(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(SetNewElection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.setAttribute("electionErrors", electionValidator.getErrorMessages());
            try {
                request.getRequestDispatcher("ElectionController?electionAction=FIND_ELECTIONS")
                        .include(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(SetNewElection.class.getName()).log(Level.SEVERE, null, ex);
            }
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
