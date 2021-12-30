package fsm;

public class FSM<E> {

	private State state;
	private Transition[] transitions;
	private State defaultState;

	public FSM(Transition[] transitions, State defaultState) {
		this.transitions = transitions;
		this.defaultState = defaultState;
		state = defaultState;
	}

	public State getState() {
		return state;
	}

	public void sendEvent(Event<E> e) {
		Transition<E> t = findTransition(e);
		if (t != null)
			state = t.newState;
		else
			state = defaultState;
	}

	private Transition<E> findTransition(Event<E> e) {
		for (Transition<E> t : transitions)
			if (transitionsMatches(e, t))
				return t;

		return null;
	}

	private boolean transitionsMatches(Event<E> e, Transition<E> t) {
		return state.equals(t.initialState) && t.event.value().equals(e.value());
	}

}
