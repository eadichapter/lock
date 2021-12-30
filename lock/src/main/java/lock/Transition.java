package lock;

public class Transition<E> {

	public State initialState;
	public State newState;
	public Event<E> event;

	public Transition(State initialState, Event<E> event, State newState) {
		this.initialState = initialState;
		this.event = event;
		this.newState = newState;
	}

}
