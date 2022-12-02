package controller.profile;

import controller.ControllerAction;

public enum ProfileActionType {
    FIND_ALL_PROFILE {
        @Override
        public ControllerAction getProfileAction() {
            return new FindAllProfiles();
        }

    },
    FIND_BY_ROLE_AND_STATUS {
        @Override
        public ControllerAction getProfileAction() {
            return new FindByRoleAndStatus();
        }
    },
    SORT_BY_CRITERIA {

        @Override
        public ControllerAction getProfileAction() {
            return new SortByCriteria();
        }
    },

    APPROVE_USER {
        @Override
        public ControllerAction getProfileAction() {
            return new ApproveUser();
        }

    },
    DELETE_USER {
        @Override
        public ControllerAction getProfileAction() {
            return new DeleteUser();
        }
        
    },
    UPDATE_USER {
        @Override
        public ControllerAction getProfileAction() {
            return new UpdateUserProfile();
        }
        
    },
    FIND_CURRENT_PROFILE {
        @Override
        public ControllerAction getProfileAction() {
            return new FindCurrentProfile();
        }
        
    },
    FIND_BY_TP {
        @Override
        public ControllerAction getProfileAction() {
            return new FindByTp();
        }
        
    };

    public abstract ControllerAction getProfileAction();
}
