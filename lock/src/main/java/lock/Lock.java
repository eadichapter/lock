package lock;

import static lock.State.UNLOCKED;
import static lock.State.WAITING_FOR_3;
import static lock.State.WAITING_FOR_FIRST_1;
import static lock.State.WAITING_FOR_LAST_1;

public class Lock {

	private static final State DEFAULT_STATE = State.WAITING_FOR_FIRST_1;

	private Transition[] rules;
	private State state;


	public Lock() {
		rules = rules();
		state = DEFAULT_STATE;
	}

	public void accept(String key) {
		Transition<String> t = findTransition(key);
		if (t != null)
			state = t.newState;
		else
			state = DEFAULT_STATE;
	}

	public boolean isLocked() {
		return !State.UNLOCKED.equals(state);
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

	private Transition<String> findTransition(String event) {
		for (Transition<String> t : rules)
			if (state.equals(t.initialState) && t.event.value().equals(event))
				return t;

		return null;
	}

}
