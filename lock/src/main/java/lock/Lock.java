package lock;

import static lock.State.UNLOCKED;
import static lock.State.WAITING_FOR_3;
import static lock.State.WAITING_FOR_FIRST_1;
import static lock.State.WAITING_FOR_LAST_1;

import java.util.ArrayList;
import java.util.List;

public class Lock {

	private List<String> acceptedKeys;
	private Transition[] rules;
	private State state;


	public Lock() {
		acceptedKeys = new ArrayList<String>();
		rules = rules();
		state = State.WAITING_FOR_FIRST_1;
	}

	public void accept(String key) {
		for (Transition<String> t : rules) {
			if (state.equals(t.initialState) && t.event.value().equals(key)) {
				state = t.newState;
			}
		}
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

}
