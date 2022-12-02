package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Profile;
import model.Seat;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-12-01T21:43:10")
@StaticMetamodel(Contester.class)
public class Contester_ { 

    public static volatile SingularAttribute<Contester, Profile> contesterProfile;
    public static volatile SingularAttribute<Contester, Integer> votes;
    public static volatile SingularAttribute<Contester, Long> id;
    public static volatile SingularAttribute<Contester, Seat> contested;

}