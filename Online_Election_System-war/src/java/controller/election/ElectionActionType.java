package controller.election;

import controller.ControllerAction;

public enum ElectionActionType {
    SET_NEW_ELECTION {
        @Override
        public ControllerAction getElectionAction() {
            return new SetNewElection();
        }

    },
    FIND_ELECTIONS {
        @Override
        public ControllerAction getElectionAction() {
            return new FindElections();
        }

    },
    FIND_SELECTED_ELECTION {
        @Override
        public ControllerAction getElectionAction() {
            return new FindSelectedElection();
        }

    },
    ADD_NEW_SEAT {
        @Override
        public ControllerAction getElectionAction() {
            return new AddNewSeat();
        }

    },
    CONTEST_SEAT {
        @Override
        public ControllerAction getElectionAction() {
            return new ContestSeat();
        }

    },
    VOTE {
        @Override
        public ControllerAction getElectionAction() {
            return new Vote();
        }

    },
    CONCLUDE_ELECTION {
        @Override
        public ControllerAction getElectionAction() {
            return new ConcludeElection();
        }

    };

    public abstract ControllerAction getElectionAction();
}
