package controller.profile;

import controller.ControllerAction;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Profile;
import model.facade.ProfileFacade;
import utility.Validator;

public class UpdateUserProfile implements ControllerAction {

    private final ProfileFacade profileFacade = lookupProfileFacadeBean();
    private final String userProfilePageLanding = "http://localhost:8080/Online_Election_System-war/ProfileController?profileAction=FIND_CURRENT_PROFILE";
    private final String userProfilePageSubsequent = "http://localhost:8080/Online_Election_System-war/ProfileController";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String tpNumber = request.getParameter("tpNumber");
        String programme = request.getParameter("programme");

        Profile foundProfile = profileFacade.find(Long.parseLong(request.getParameter("user_id")));

        if (foundProfile != null) {

            Validator<Profile> profileValidator = new Validator<>(foundProfile);
            boolean result = profileValidator
                    .validate(profile -> profile.getPassword()
                    .matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"),
                            "password",
                            "Password does not meet requirements")
                    .validate(profile -> confirmPassword.equals(password),
                            "confirmPassword",
                            "Passwords do not match")
                    .validate((profile) -> {
                        String number = profile.getTpNumber();
                        if (number.startsWith("TP")) {
                            number = profile.getTpNumber().substring(2);
                        }

                        return number.length() == 6 && number.matches("[0-9]+");
                    }, "tpNumber", "Invalid TP Number")
                    .getValidationResult();

            request.setAttribute("allProfiles", profileFacade.findActiveProfiles());

            if (result == true) {
                foundProfile.setUsername(username);
                foundProfile.setPassword(password);
                foundProfile.setTpNumber(tpNumber);
                foundProfile.setProgramme(programme);
                profileFacade.edit(foundProfile);

                request.setAttribute("updatedUserTp", foundProfile.getTpNumber());

                try {
                    System.out.println(request.getHeader("referer"));
                    if (request.getHeader("referer").equals(userProfilePageLanding)
                            || request.getHeader("referer").equals(userProfilePageSubsequent)) {
                        request.getRequestDispatcher("ProfileController?profileAction=FIND_CURRENT_PROFILE")
                                .include(request, response);
                    } else {
                        request.getRequestDispatcher("users.jsp").include(request, response);
                    }
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(UpdateUserProfile.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                request.setAttribute("updateErrorList", profileValidator.getErrorMessages());
                try {
                    if (request.getHeader("referer").equals(userProfilePageLanding)
                            || request.getHeader("referer").equals(userProfilePageSubsequent)) {
                        request.getRequestDispatcher("ProfileController?profileAction=FIND_CURRENT_PROFILE")
                                .include(request, response);
                    } else {
                        request.getRequestDispatcher("users.jsp").include(request, response);
                    }

                } catch (ServletException | IOException ex) {
                    Logger.getLogger(UpdateUserProfile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private ProfileFacade lookupProfileFacadeBean() {
        try {
            Context c = new InitialContext();
            return (ProfileFacade) c.lookup("java:global/Online_Election_System/Online_Election_System-ejb/ProfileFacade!model.facade.ProfileFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
