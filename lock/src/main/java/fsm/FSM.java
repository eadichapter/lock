package fsm;

public class FSM<E, C> {

	private State state;
	private Transition[] transitions;
	private C lock;
	private Transition<E, C> defaultTransition;

	public FSM(Transition[] transitions, C lock, Transition<E, C> defaultTransition) {
		this.transitions = transitions;
		this.defaultTransition = defaultTransition;
		this.lock = lock;
		this.state = defaultTransition.newState;
	}

	public State getState() {
		return state;
	}

	public void sendEvent(Event<E> e) {
		Transition<E, C> t = findTransition(e);
		if (t != null)
			executeTransition(t);
		else
			executeTransition(defaultTransition);
	}

	private void executeTransition(Transition<E, C> t) {
		state = t.newState;
		if (t.action != null)
			t.action.accept(lock);
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
