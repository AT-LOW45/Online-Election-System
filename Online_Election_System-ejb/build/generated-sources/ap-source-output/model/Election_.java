package model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Seat;
import model.Voter;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-16T23:28:58")
@StaticMetamodel(Election.class)
public class Election_ { 

    public static volatile SingularAttribute<Election, LocalDate> endDate;
    public static volatile ListAttribute<Election, Voter> voters;
    public static volatile SingularAttribute<Election, Long> id;
    public static volatile SingularAttribute<Election, String> title;
    public static volatile ListAttribute<Election, Seat> seats;
    public static volatile SingularAttribute<Election, LocalDate> startDate;
    public static volatile SingularAttribute<Election, Boolean> status;

}