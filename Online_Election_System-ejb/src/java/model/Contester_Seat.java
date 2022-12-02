package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
    @NamedQuery(name = "Contester_Seat.findByElectionId", query = "SELECT con_seat FROM Contester_Seat con_seat WHERE con_seat.election.id = :electionId"),
    @NamedQuery(name = "Contester_Seat.findByContesterIdAndElectionId", query = "SELECT con_seat FROM Contester_Seat con_seat WHERE con_seat.contester.id = :contesterId AND con_seat.election.id = :electionId"),
    @NamedQuery(name = "Contester_Seat.findByContesterId", query = "SELECT con_seat FROM Contester_Seat con_seat WHERE con_seat.contester.id = :contesterId"),
        @NamedQuery(name = "Contester_Seat.findBySeatId", query = "SELECT con_seat FROM Contester_Seat con_seat WHERE con_seat.seat.id = :seatId"),
})
public class Contester_Seat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "contester_id", referencedColumnName = "id")
    private Contester contester;
    
    @OneToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;
    
    @OneToOne
    @JoinColumn(name = "election_id", referencedColumnName = "id")
    private Election election;
    
    private int votes;
    
    public Contester_Seat(Contester contester, Seat seat, Election election, int votes) {
        this.contester = contester;
        this.seat = seat;
        this.election = election;
        this.votes = votes;
    }
    
    public Contester_Seat() {}

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
    

    public Contester getContester() {
        return contester;
    }

    public void setContester(Contester contester) {
        this.contester = contester;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
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
        if (!(object instanceof Contester_Seat)) {
            return false;
        }
        Contester_Seat other = (Contester_Seat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Contester_Seat[ id=" + id + " ]";
    }
    
}
