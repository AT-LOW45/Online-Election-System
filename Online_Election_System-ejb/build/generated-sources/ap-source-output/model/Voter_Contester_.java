package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Contester;
import model.Election;
import model.Voter;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-03-16T23:28:58")
@StaticMetamodel(Voter_Contester.class)
public class Voter_Contester_ { 

    public static volatile SingularAttribute<Voter_Contester, Election> election;
    public static volatile SingularAttribute<Voter_Contester, Long> id;
    public static volatile SingularAttribute<Voter_Contester, Voter> voter;
    public static volatile SingularAttribute<Voter_Contester, Contester> contester;

}