/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import controller.ControllerAction;
import controller.ControllerAction;
import controller.authentication.Login;
import controller.authentication.Logout;

/**
 *
 * @author Koh
 */
public enum AuthActionType {
    LOGIN {
        @Override
        public ControllerAction getAction() {
            return new Login();
        }

    },
    LOGOUT {
        @Override
        public ControllerAction getAction() {
            return new Logout();
        }
        
    },
    REGISTER {
        @Override
        public ControllerAction getAction() {
            return new Register();
        }
        
    };

    public abstract ControllerAction getAction();
}
