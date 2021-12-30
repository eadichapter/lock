package fsm;

public class Event<T> {

	private T value;

	public Event(T value) {
		this.value = value;
	}

	public T value() {
		return value;
	}
}
