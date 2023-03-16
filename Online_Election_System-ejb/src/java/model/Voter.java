package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
    @NamedQuery(name = "Voter.findByProfileId", query = "SELECT vot FROM Voter vot WHERE vot.voterProfile.id = :profileId"),})
public class Voter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JoinColumn(name = "election_id", referencedColumnName = "id")
    @ManyToOne
    private Election election;
    
    @JoinColumn(name = "voter_profile_id", referencedColumnName = "id")
    @OneToOne
    private Profile voterProfile;
    
    public Voter() {}
    
    public Voter(Profile profile) {
        this.voterProfile = profile;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Profile getVoterProfile() {
        return voterProfile;
    }

    public void setVoterProfile(Profile voterProfile) {
        this.voterProfile = voterProfile;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voter)) {
            return false;
        }
        Voter other = (Voter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Voter[ id=" + id + " ]";
    }
    
}
