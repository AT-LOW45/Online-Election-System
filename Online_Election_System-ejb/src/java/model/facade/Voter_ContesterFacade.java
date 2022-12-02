package model.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Voter_Contester;

@Stateless
public class Voter_ContesterFacade extends AbstractFacade<Voter_Contester> {

    @PersistenceContext(unitName = "Online_Election_System-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Voter_ContesterFacade() {
        super(Voter_Contester.class);
    }

    public List<Voter_Contester> findByElectionId(Long electionId) {
        Query searchElectionQuery = em.createNamedQuery("Voter_Contester.findByElectionId");
        searchElectionQuery.setParameter("electionId", electionId);
        return searchElectionQuery.getResultList();
    }

}
