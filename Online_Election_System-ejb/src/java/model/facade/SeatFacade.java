package model.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Seat;

@Stateless
public class SeatFacade extends AbstractFacade<Seat> {

    @PersistenceContext(unitName = "Online_Election_System-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeatFacade() {
        super(Seat.class);
    }

    public List<Seat> findByElectionId(Long electionId) {
        Query searchByElectionQuery = em.createNamedQuery("Seat.findByElectionId");
        searchByElectionQuery.setParameter("electionId", electionId);
        return searchByElectionQuery.getResultList();
    }

}
