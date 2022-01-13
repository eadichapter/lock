package fsm;

import java.util.List;

public class FSM<E, C> {

	private State state;
	private List<Transition<E, C>> transitions;
	private C actionConsumer;
	private Transition<E, C> defaultTransition;

	public FSM(List<Transition<E, C>> transitions, Transition<E, C> defaultTransition, C actionConsumer) {
		this.transitions = transitions;
		this.defaultTransition = defaultTransition;
		this.actionConsumer = actionConsumer;
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
			t.action.accept(actionConsumer);
	}

	private Transition<E, C> findTransition(Event<E> e) {
		for (Transition<E, C> t : transitions)
			if (transitionsMatch(e, t))
				return t;

		return null;
	}

	private boolean transitionsMatch(Event<E> e, Transition<E, C> t) {
		return state.equals(t.initialState) && t.event.value().equals(e.value());
	}

}
