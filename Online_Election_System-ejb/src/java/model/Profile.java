package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import role.Role;

@Entity
@NamedQueries({
    @NamedQuery(name = "Profile.authenticateUser", query = "SELECT pro FROM Profile pro WHERE pro.username = :username AND pro.password = :password")
    ,
    @NamedQuery(name = "Profile.findByStatusAndRole", query = "SELECT pro FROM Profile pro WHERE pro.role = :role AND pro.status = :status AND pro.active = true")
    ,
    @NamedQuery(name = "Profile.sortByStatus", query = "SELECT pro FROM Profile pro WHERE pro.active = true ORDER BY pro.status")
    ,
    @NamedQuery(name = "Profile.sortByEarliestJoined", query = "SELECT pro FROM Profile pro WHERE pro.active = true ORDER BY pro.dateJoined ASC")
    ,
    @NamedQuery(name = "Profile.sortByLatestJoined", query = "SELECT pro FROM Profile pro WHERE pro.active = true ORDER BY pro.dateJoined DESC")
    ,
    @NamedQuery(name = "Profile.findByTpNumber", query = "SELECT pro FROM Profile pro WHERE pro.tpNumber = :tpNumber AND pro.active = true"),
    @NamedQuery(name = "Profile.findActiveProfiles", query = "SELECT pro FROM Profile pro WHERE pro.active = true"),
})
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "tp_number")
    private String tpNumber;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status")
    private boolean status;

    @Column(name = "programme")
    private String programme;

    @Column(name = "date_joined", columnDefinition = "DATE")
    private LocalDate dateJoined;

    private boolean active;

    public Profile(String username,
            String password,
            String tpNumber,
            Role role,
            String programme,
            boolean status,
            LocalDate dateJoined) {
        this.username = username;
        this.password = password;
        this.tpNumber = tpNumber;
        this.role = role;
        this.programme = programme;
        this.status = status;
        this.dateJoined = dateJoined;
        this.active = true;
    }

    public Profile() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getDateJoined() {
        return this.dateJoined.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTpNumber() {
        return tpNumber;
    }

    public void setTpNumber(String tpNumber) {
        this.tpNumber = tpNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.User[ id=" + id + " ]";
    }

}
