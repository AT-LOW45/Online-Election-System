package model.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Profile;
import role.Role;

@Stateless
public class ProfileFacade extends AbstractFacade<Profile> {

    @PersistenceContext(unitName = "Online_Election_System-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfileFacade() {
        super(Profile.class);
    }
    
    public List<Profile> findActiveProfiles() {
        Query findActiveProfilesQuery = em.createNamedQuery("Profile.findActiveProfiles");
        return findActiveProfilesQuery.getResultList();
    }

    public Profile authenticateUser(String username, String password) {
        Profile foundProfile = null;
        Query userAuthenticationQuery = em.createNamedQuery("Profile.authenticateUser");
        userAuthenticationQuery.setParameter("username", username);
        userAuthenticationQuery.setParameter("password", password);

        List<Profile> data = userAuthenticationQuery.getResultList();
        if (data.size() > 0) {
            foundProfile = data.get(0);
        }
        return foundProfile;
    }

    public List<Profile> findStatusAndRole(boolean status, Role role) {
        Query findUsersQuery = em.createNamedQuery("Profile.findByStatusAndRole");
        findUsersQuery.setParameter("status", status);
        findUsersQuery.setParameter("role", role);
        return findUsersQuery.getResultList();
    }

    public List<Profile> findByTpNumber(String tpNumber) {
        Query findByTpQuery = em.createNamedQuery("Profile.findByTpNumber");
        findByTpQuery.setParameter("tpNumber", tpNumber);
        return findByTpQuery.getResultList();
    }

    public List<Profile> sortByStatus() {
        Query sortByStatusQuery = em.createNamedQuery("Profile.sortByStatus");
        return sortByStatusQuery.getResultList();
    }

    public List<Profile> sortByEarliestJoined() {
        Query sortByDateQuery = em.createNamedQuery("Profile.sortByEarliestJoined");
        return sortByDateQuery.getResultList();
    }

    public List<Profile> sortByLatestJoined() {
        Query sortByDateQuery = em.createNamedQuery("Profile.sortByLatestJoined");
        return sortByDateQuery.getResultList();
    }
}
