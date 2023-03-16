/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Koh
 */
import controller.authentication.AuthActionType;
import controller.election.ElectionActionType;
import controller.profile.ProfileActionType;

public class ActionFactory {
    public static ControllerAction getAuthAction(AuthActionType type) {
        return type.getAction();
    };
    
    public static ControllerAction getProfileAction(ProfileActionType type) {
        return type.getProfileAction();
    }
    
    public static ControllerAction getElectionAction(ElectionActionType type) {
        return type.getElectionAction();
    }


}
