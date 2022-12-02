package model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import role.Role;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-12-01T21:43:10")
@StaticMetamodel(Profile.class)
public class Profile_ { 

    public static volatile SingularAttribute<Profile, String> password;
    public static volatile SingularAttribute<Profile, Role> role;
    public static volatile SingularAttribute<Profile, String> tpNumber;
    public static volatile SingularAttribute<Profile, LocalDate> dateJoined;
    public static volatile SingularAttribute<Profile, Boolean> active;
    public static volatile SingularAttribute<Profile, Long> id;
    public static volatile SingularAttribute<Profile, String> programme;
    public static volatile SingularAttribute<Profile, String> username;
    public static volatile SingularAttribute<Profile, Boolean> status;

}