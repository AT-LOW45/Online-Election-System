package controller.authentication;

import controller.ControllerAction;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Contester;
import model.Profile;
import model.Voter;
import model.facade.ContesterFacade;
import model.facade.ProfileFacade;
import model.facade.VoterFacade;
import role.Role;
import utility.Validator;

public class Register implements ControllerAction {

    private final VoterFacade voterFacade = lookupVoterFacadeBean();
    private final ContesterFacade contesterFacade = lookupContesterFacadeBean();
    private final ProfileFacade memberFacade = lookupProfileFacadeBean();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String tpNumber = request.getParameter("tpNumber");
        Role role = Role.valueOf(request.getParameter("role"));
        String programme = request.getParameter("programme");

        LocalDate today = LocalDate.now();

        Profile newProfile = new Profile(username, password, tpNumber, role, programme, false, today);

        Validator<Profile> profileValidator = new Validator<>(newProfile);
        boolean result = profileValidator
                .validate(
                        profile -> profile.getPassword()
                                .matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"),
                        "password",
                        "Password does not meet requirements")
                .validate(profile -> confirmPassword.equals(profile.getPassword()),
                        "confirmPassword",
                        "Passwords do not match")
                .validate((profile) -> {
                    String number = profile.getTpNumber();
                    if (number.startsWith("TP")) {
                        number = profile.getTpNumber().substring(2);
                    }
                    boolean dupTp = memberFacade.findAll().stream()
                            .filter(member -> member.getTpNumber().equals(tpNumber))
                            .findFirst()
                            .isPresent();
                    return number.length() == 6 && number.matches("[0-9]+") && !dupTp;
                }, "tpNumber", "Invalid TP Number")
                .getValidationResult();

        try {
            if (result == true) {
                memberFacade.create(newProfile);
                if (role.equals(Role.CONTESTER)) {
                    Contester newContester = new Contester();
                    newContester.setContesterProfile(newProfile);
                    contesterFacade.create(newContester);

                } else if (role.equals(Role.VOTER)) {
                    Voter newVoter = new Voter();
                    newVoter.setVoterProfile(newProfile);
                    voterFacade.create(newVoter);
                }

                response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");

                HttpSession userSession = request.getSession(false);
                userSession.setAttribute("userId", newProfile.getId());
                userSession.setAttribute("username", newProfile.getUsername());
                userSession.setAttribute("role", newProfile.getRole());
                userSession.setAttribute("tpNumber", newProfile.getTpNumber());
                userSession.setAttribute("approved", newProfile.isStatus());

                if (newProfile.getRole().equals(Role.COMMITTEE)) {
                    request.getRequestDispatcher("ProfileController?profileAction=FIND_ALL_PROFILE")
                            .include(request, response);
                } else {
                    request.getRequestDispatcher("ElectionController?electionAction=FIND_ELECTIONS")
                            .include(request, response);
                }

            } else {
                request.setAttribute("errorList", profileValidator.getErrorMessages());
                request.getRequestDispatcher("register.jsp").include(request, response);
            }
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private ProfileFacade lookupProfileFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProfileFacade) c.lookup(
                    "java:global/Online_Election_System/Online_Election_System-ejb/ProfileFacade!model.facade.ProfileFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ContesterFacade lookupContesterFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ContesterFacade) c.lookup(
                    "java:global/Online_Election_System/Online_Election_System-ejb/ContesterFacade!model.facade.ContesterFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private VoterFacade lookupVoterFacadeBean() {
        try {
            Context c = new InitialContext();
            return (VoterFacade) c.lookup(
                    "java:global/Online_Election_System/Online_Election_System-ejb/VoterFacade!model.facade.VoterFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
