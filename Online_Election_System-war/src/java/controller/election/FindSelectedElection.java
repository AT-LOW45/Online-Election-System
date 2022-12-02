package controller.election;

import controller.ControllerAction;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Contester;
import model.Election;
import model.Seat;
import model.Voter;
import model.facade.ContesterFacade;
import model.facade.Contester_SeatFacade;
import model.facade.ElectionFacade;
import model.facade.SeatFacade;
import model.facade.VoterFacade;
import model.facade.Voter_ContesterFacade;

import role.Role;

public class FindSelectedElection implements ControllerAction {

    private final Voter_ContesterFacade voter_ContesterFacade = lookupVoter_ContesterFacadeBean();
    private final Contester_SeatFacade contester_SeatFacade = lookupContester_SeatFacadeBean();
    private final VoterFacade voterFacade = lookupVoterFacadeBean();
    private final ContesterFacade contesterFacade = lookupContesterFacadeBean();
    private final SeatFacade seatFacade = lookupSeatFacadeBean();
    private final ElectionFacade electionFacade = lookupElectionFacadeBean();
    private final Map<Contester, Seat> winnerSeatMapping = new HashMap<>();
    private final Map<Contester, Long> contesterVoteMapping = new HashMap<>();
    private final Map<Seat, Long> seatContesterMapping = new HashMap<>();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        Long electionId = Long.parseLong(request.getParameter("election_id"));
        Election election = electionFacade.find(electionId);
        Role role = (Role) request.getSession(false).getAttribute("role");

        request.setAttribute("selectedElection", election);

        List<Voter> voters = voterFacade.findAll();
        double voteRate = voters.isEmpty() ? 0
                : (((double)voter_ContesterFacade.findByElectionId(electionId).size()) / voters.size()) * 100;
        request.setAttribute("voteRate", voteRate);

        if (election.isStatus() == true) {
            List<Contester> contesters = electionFacade.find(Long.parseLong(request.getParameter("election_id")))
                    .getSeats()
                    .stream()
                    .flatMap(seat -> seat.getContesters().stream())
                    .collect(Collectors.toList());
            request.setAttribute("contesters", contesters);
        }

        if (election.isStatus() == false) {

            // find winner for each seat
            contester_SeatFacade.findByElectionId(electionId)
                    .stream()
                    .map(map -> map.getSeat())
                    .collect(Collectors.toSet())
                    .forEach(seat -> {
                        Optional<Contester> winner = contester_SeatFacade.findBySeatId(seat.getId())
                                .stream()
                                .map(map -> map.getContester())
                                .collect(Collectors.toList())
                                .stream()
                                .max(Comparator.comparing(con -> contester_SeatFacade
                                .findByContesterId(con.getId()).getVotes()));

                        if (winner.isPresent()) {
                            winnerSeatMapping.put(winner.get(), seat);
                        } else {
                            winnerSeatMapping.put(null, seat);
                        }
                    });

            election.getSeats().forEach(seat -> {
                if (!winnerSeatMapping.containsValue(seat)) {
                    winnerSeatMapping.put(null, seat);
                }
            });

            request.setAttribute("winnerSeatMapping", winnerSeatMapping);
        }

        if (role.equals(Role.COMMITTEE)) {

            // find number of votes for each contester
            voter_ContesterFacade.findByElectionId(electionId)
                    .stream()
                    .collect(Collectors.groupingBy(map -> map.getContester(), Collectors.counting()))
                    .forEach((contester, votes) -> {
                        contesterVoteMapping.put(contester, votes);
                    });
            contester_SeatFacade.findByElectionId(electionId)
                    .forEach(conSeatMap -> {
                        if (!contesterVoteMapping.containsKey(conSeatMap.getContester())) {
                            contesterVoteMapping.put(conSeatMap.getContester(), 0L);
                        }
                    });

            // find number of contesters for each seat
            contester_SeatFacade.findByElectionId(electionId)
                    .stream()
                    .collect(Collectors.groupingBy(map -> map.getSeat(), Collectors.counting()))
                    .forEach((seat, noOfContesters) -> {
                        seatContesterMapping.put(seat, noOfContesters);
                    });
            seatFacade.findByElectionId(electionId)
                    .forEach(seat -> {
                        if (!seatContesterMapping.containsKey(seat)) {
                            seatContesterMapping.put(seat, 0L);
                        }
                    });

            request.setAttribute("contesterVoteMapping", contesterVoteMapping);
            request.setAttribute("seatContesterMapping", seatContesterMapping);

        }

        if (role.equals(Role.VOTER)) {
            Voter voter = voterFacade.findByProfileId((Long) request.getSession(false).getAttribute("userId"));
            request.setAttribute("loggedInVoter", voter);
        }

        if (role.equals(Role.CONTESTER)) {
            Contester contester = contesterFacade.findByProfileId((Long) request.getSession(false).getAttribute("userId"));
            request.setAttribute("loggedInContester", contester);
        }

        try {
            request.getRequestDispatcher("election_details.jsp").include(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(FindSelectedElection.class.getName()).log(Level.SEVERE, null, ex);
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

    private Contester_SeatFacade lookupContester_SeatFacadeBean() {
        try {
            Context c = new InitialContext();
            return (Contester_SeatFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/Contester_SeatFacade!model.facade.Contester_SeatFacade");
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

}
