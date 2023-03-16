package model.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Contester;

@Stateless
public class ContesterFacade extends AbstractFacade<Contester> {

    @PersistenceContext(unitName = "Online_Election_System-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContesterFacade() {
        super(Contester.class);
    }

    public Contester findByProfileId(Long profileId) {
        Query userAuthenticationQuery = em.createNamedQuery("Contester.findByProfileId");
        userAuthenticationQuery.setParameter("profileId", profileId);

        if (userAuthenticationQuery.getResultList().isEmpty()) {
            return null;
        } else {
            return (Contester) userAuthenticationQuery.getResultList().get(0);
        }

    }

}
