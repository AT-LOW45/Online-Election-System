package model.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Contester_Seat;

@Stateless
public class Contester_SeatFacade extends AbstractFacade<Contester_Seat> {

    @PersistenceContext(unitName = "Online_Election_System-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Contester_SeatFacade() {
        super(Contester_Seat.class);
    }

    public List<Contester_Seat> findByElectionId(Long electionId) {
        Query searchElectionQuery = em.createNamedQuery("Contester_Seat.findByElectionId");
        searchElectionQuery.setParameter("electionId", electionId);
        return searchElectionQuery.getResultList();
    }

    public Contester_Seat findByContesterId(Long contesterId) {
        Query searchContesterQuery = em.createNamedQuery("Contester_Seat.findByContesterId");
        searchContesterQuery.setParameter("contesterId", contesterId);

        if (searchContesterQuery.getResultList().isEmpty()) {
            return null;
        } else {
            return (Contester_Seat) searchContesterQuery.getResultList().get(0);
        }
    }

    public List<Contester_Seat> findBySeatId(Long seatId) {
        Query searchSeatQuery = em.createNamedQuery("Contester_Seat.findBySeatId");
        searchSeatQuery.setParameter("seatId", seatId);
        return searchSeatQuery.getResultList();
    }

    public Contester_Seat findByContesterIdAndElectionId(Long contesterId, Long electionId) {
        Query searchContesterQuery = em.createNamedQuery("Contester_Seat.findByContesterIdAndElectionId");
        searchContesterQuery.setParameter("contesterId", contesterId);
        searchContesterQuery.setParameter("electionId", electionId);

        if (searchContesterQuery.getResultList().isEmpty()) {
            return null;
        } else {
            return (Contester_Seat) searchContesterQuery.getResultList().get(0);
        }
    }

}
