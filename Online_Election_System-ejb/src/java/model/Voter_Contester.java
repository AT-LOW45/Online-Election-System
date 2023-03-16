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
    @NamedQuery(name = "Voter_Contester.findByElectionId", query = "SELECT vote_con FROM Voter_Contester vote_con WHERE vote_con.election.id = :electionId"),})
public class Voter_Contester implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "voter_id", referencedColumnName = "id")
    private Voter voter;
    
    @OneToOne
    @JoinColumn(name = "contester_id", referencedColumnName = "id")
    private Contester contester;
    
    @OneToOne
    @JoinColumn(name = "election_id", referencedColumnName = "id")
    private Election election;
    
    public Voter_Contester(Contester contester, Voter voter, Election election) {
        this.contester = contester;
        this.voter = voter;
        this.election = election;
    }
    
    public Voter_Contester() {}

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Contester getContester() {
        return contester;
    }

    public void setContester(Contester contester) {
        this.contester = contester;
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
        if (!(object instanceof Voter_Contester)) {
            return false;
        }
        Voter_Contester other = (Voter_Contester) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Voter_Contester[ id=" + id + " ]";
    }
    
}
