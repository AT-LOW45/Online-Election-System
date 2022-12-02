package model.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Voter;

@Stateless
public class VoterFacade extends AbstractFacade<Voter> {

    @PersistenceContext(unitName = "Online_Election_System-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoterFacade() {
        super(Voter.class);
    }

    public Voter findByProfileId(Long profileId) {
        Query findProfileQuery = em.createNamedQuery("Voter.findByProfileId");
        findProfileQuery.setParameter("profileId", profileId);

        if (findProfileQuery.getResultList().isEmpty()) {
            return null;
        } else {
            return (Voter) findProfileQuery.getResultList().get(0);
        }

    }

}
