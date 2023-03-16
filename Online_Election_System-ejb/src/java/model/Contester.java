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
    @NamedQuery(name = "Contester.findByProfileId", query = "SELECT con FROM Contester con WHERE con.contesterProfile.id = :profileId"),})
public class Contester implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "contester_profile_id", referencedColumnName = "id")
    @OneToOne
    private Profile contesterProfile;

    private int votes;

    @JoinColumn(name = "contested_seat_id", referencedColumnName = "id")
    @ManyToOne
    private Seat contested;

    public Contester() {
    }
    
    public Contester(Profile profile, Seat contested, int votes) {
        this.contesterProfile = profile;
        this.contested = contested;
        this.votes = votes;
    }

    public Contester(Profile profile, boolean status) {
        this.contesterProfile = profile;
    }

    public Profile getContesterProfile() {
        return contesterProfile;
    }

    public void setContesterProfile(Profile contesterProfile) {
        this.contesterProfile = contesterProfile;
    }

    public int getVotes() {
        return votes;
    }

    public Seat getContested() {
        return contested;
    }

    public void setContested(Seat contested) {
        this.contested = contested;
    }

    public void setVotes(int votes) {
        this.votes = votes;
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
        if (!(object instanceof Contester)) {
            return false;
        }
        Contester other = (Contester) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Contester[ id=" + id + " ]";
    }

}
