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
	private FSM<String, Lock> fsm;
	private String color;

	public Lock() {
		fsm = new FSM<String, Lock>(rules(), DEFAULT_STATE, this, defaultTransition());
	}

	private Transition<String, Lock> defaultTransition() {
		return new Transition<String, Lock>(null, null, DEFAULT_STATE, t -> t.setLampColor("red"));
	}

	public void accept(String key) {
		fsm.sendEvent(new Event<String>(key));
	}

	public boolean isLocked() {
		return !LockState.UNLOCKED.equals(fsm.getState());
	}

	public void setLampColor(String color) {
		this.color = color;
	}

	public String lampColor()  {
		return color;
	}

	private Transition[] rules() {
		Transition[] transitions = {
			new Transition<String, Lock>(WAITING_FOR_FIRST_1, new Event<String>("1"), WAITING_FOR_3, t -> t.setLampColor("red")),
			new Transition<String, Lock>(WAITING_FOR_3, new Event<String>("3"), WAITING_FOR_LAST_1, t -> t.setLampColor("red")),
			new Transition<String, Lock>(WAITING_FOR_3, new Event<String>("1"), WAITING_FOR_3, t -> t.setLampColor("red")),
			new Transition<String, Lock>(WAITING_FOR_LAST_1, new Event<String>("1"), UNLOCKED, t -> t.setLampColor("green")),
		};
		return transitions;
	}

}
