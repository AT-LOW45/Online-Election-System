package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Contester;
import model.Election;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-16T23:28:58")
@StaticMetamodel(Seat.class)
public class Seat_ { 

    public static volatile SingularAttribute<Seat, Integer> contesterLimit;
    public static volatile SingularAttribute<Seat, Election> election;
    public static volatile ListAttribute<Seat, Contester> contesters;
    public static volatile SingularAttribute<Seat, String> seatName;
    public static volatile SingularAttribute<Seat, Long> id;

}