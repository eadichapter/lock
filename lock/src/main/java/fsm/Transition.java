package fsm;

import java.util.function.Consumer;

public class Transition<E, C> {

	public State initialState;
	public State newState;
	public Event<E> event;
	public Consumer<C> action;

	public Transition(State initialState, Event<E> event, State newState, Consumer<C> action) {
		this.initialState = initialState;
		this.event = event;
		this.newState = newState;
		this.action = action;
	}

}
