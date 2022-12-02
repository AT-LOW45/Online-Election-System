package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Contester;
import model.Election;
import model.Seat;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-12-01T21:43:10")
@StaticMetamodel(Contester_Seat.class)
public class Contester_Seat_ { 

    public static volatile SingularAttribute<Contester_Seat, Seat> seat;
    public static volatile SingularAttribute<Contester_Seat, Election> election;
    public static volatile SingularAttribute<Contester_Seat, Integer> votes;
    public static volatile SingularAttribute<Contester_Seat, Long> id;
    public static volatile SingularAttribute<Contester_Seat, Contester> contester;

}