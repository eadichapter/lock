package lock;

import fsm.State;

public enum LockState implements State {
	WAITING_FOR_FIRST_1, WAITING_FOR_3, WAITING_FOR_LAST_1, UNLOCKED
}
