package fsm;

public class FSM<E, C> {

	private State state;
	private Transition[] transitions;
	private State defaultState;
	private C lock;
	private Transition defaultTransition;

	public FSM(Transition[] transitions, State defaultState, C lock) {
		this.transitions = transitions;
		this.defaultState = defaultState;
		this.lock = lock;
		state = defaultState;
	}

	public FSM(Transition[] transitions, State defaultState, C lock, Transition defaultTransition) {
		this(transitions, defaultState, lock);
		this.defaultTransition = defaultTransition;
	}

	public State getState() {
		return state;
	}

	public void sendEvent(Event<E> e) {
		Transition<E, C> t = findTransition(e);
		if (t != null) {
			state = t.newState;
			t.action.accept(lock);
		}
		else {
			state = defaultState;
			if (defaultTransition != null)
				defaultTransition.action.accept(lock);
		}
	}

	private Transition<E, C> findTransition(Event<E> e) {
		for (Transition<E, C> t : transitions)
			if (transitionsMatches(e, t))
				return t;

		return null;
	}

	private boolean transitionsMatches(Event<E> e, Transition<E, C> t) {
		return state.equals(t.initialState) && t.event.value().equals(e.value());
	}

}
