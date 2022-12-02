package model.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Election;

@Stateless
public class ElectionFacade extends AbstractFacade<Election> {

    @PersistenceContext(unitName = "Online_Election_System-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ElectionFacade() {
        super(Election.class);
    }

    public Election findOngoingElection() {
        Query findOngoingElectionQuery = em.createNamedQuery("Election.findOngoingElection");
        if (findOngoingElectionQuery.getResultList().isEmpty()) {
            return null;
        } else {
            return (Election) findOngoingElectionQuery.getResultList().get(0);
        }
    }

    public List<Election> findFinishedElections() {
        Query findFinishedElectionsQuery = em.createNamedQuery("Election.findFinishedElections");
        return (List<Election>) findFinishedElectionsQuery.getResultList();
    }

}
