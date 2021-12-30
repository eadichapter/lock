package lock;

import static lock.State.UNLOCKED;
import static lock.State.WAITING_FOR_3;
import static lock.State.WAITING_FOR_FIRST_1;
import static lock.State.WAITING_FOR_LAST_1;

public class Lock {

	private static final State DEFAULT_STATE = State.WAITING_FOR_FIRST_1;
	private FSM<String> fsm;

	public Lock() {
		fsm = new FSM<String>(rules(), DEFAULT_STATE);
	}

	public void accept(String key) {
		fsm.sendEvent(new Event<String>(key));
	}

	public boolean isLocked() {
		return !State.UNLOCKED.equals(fsm.getState());
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
