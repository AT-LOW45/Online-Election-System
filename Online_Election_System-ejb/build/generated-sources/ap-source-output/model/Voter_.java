package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Election;
import model.Profile;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-16T23:28:58")
@StaticMetamodel(Voter.class)
public class Voter_ { 

    public static volatile SingularAttribute<Voter, Election> election;
    public static volatile SingularAttribute<Voter, Long> id;
    public static volatile SingularAttribute<Voter, Profile> voterProfile;

}