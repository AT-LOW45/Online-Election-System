package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "Seat.findByElectionId", query = "SELECT seat FROM Seat seat WHERE seat.election.id = :electionId")
})
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "seat_name")
    private String seatName;

    @JoinColumn(name = "election_id", referencedColumnName = "id")
    @ManyToOne
    private Election election;

    @OneToMany(mappedBy = "contested")
    private List<Contester> contesters;

    @Column(name = "contester_limit")
    private int contesterLimit;

    public Seat(String seatName, int contesterLimit, Election election) {
        this.seatName = seatName;
        this.contesterLimit = contesterLimit;
        this.election = election;
    }

    public Seat() {
    }

    public int getContesterLimit() {
        return contesterLimit;
    }

    public void setContesterLimit(int contesterLimit) {
        this.contesterLimit = contesterLimit;
    }

    public List<Contester> getContesters() {
        return contesters;
    }

    public void setContesters(List<Contester> contesters) {
        this.contesters = contesters;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
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
        if (!(object instanceof Seat)) {
            return false;
        }
        Seat other = (Seat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Seat[ id=" + id + " ]";
    }

}
