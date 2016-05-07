package tools;

public class State {
	
	private static volatile State instance = null;
	private int currentState; //0:pause, 1:play

	private State() {
		super();
		this.currentState = 0;
	}

	public final static State getInstance() {
		if (State.instance == null) {
			synchronized(State.class) {
				if (State.instance == null) {
					State.instance = new State();
				}
			}
		}
		return State.instance;
	}

	public void changeState() {
		if (this.currentState == 0)
			this.currentState = 1;
		else 
			this.currentState = 0;
	}
	
	public int getState() {
		return this.currentState;
	}
	
}
