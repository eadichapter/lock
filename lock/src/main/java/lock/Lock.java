package lock;

import static lock.LockState.UNLOCKED;
import static lock.LockState.WAITING_FOR_3;
import static lock.LockState.WAITING_FOR_FIRST_1;
import static lock.LockState.WAITING_FOR_LAST_1;

import fsm.Event;
import fsm.FSM;
import fsm.Transition;

public class Lock {

	private static final LockState DEFAULT_STATE = LockState.WAITING_FOR_FIRST_1;
	private FSM<String> fsm;

	public Lock() {
		fsm = new FSM<String>(rules(), DEFAULT_STATE);
	}

	public void accept(String key) {
		fsm.sendEvent(new Event<String>(key));
	}

	public boolean isLocked() {
		return !LockState.UNLOCKED.equals(fsm.getState());
	}

	private Transition[] rules() {
		Transition[] transitions = {
			new Transition<String>(WAITING_FOR_FIRST_1, new Event<String>("1"), WAITING_FOR_3),
			new Transition<String>(WAITING_FOR_3, new Event<String>("3"), WAITING_FOR_LAST_1),
			new Transition<String>(WAITING_FOR_3, new Event<String>("1"), WAITING_FOR_3),
			new Transition<String>(WAITING_FOR_LAST_1, new Event<String>("1"), UNLOCKED),
		};
		return transitions;
	}

}
