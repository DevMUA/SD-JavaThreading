package state;

public enum SHostess {
	WAITING_FOR_FLIGHT("WTFL", ""),
	WAITING_FOR_PASSENGER("WTPS", ""),
	CHECK_PASSENGER("CKPS", "\nFlight %d: passenger %d checked.\n"),
	READY_TO_FLY("RDTF", "\nFlight %d: departed with %d passengers.\n")
	;

	private SHostess(String state, String logDiv) { 
		this.state = state; 
		this.logDiv = logDiv;
	}

	private final String state;
	private final String logDiv;

	public String logDiv(int flightNumber, int numPassenger) {
		return String.format(logDiv, flightNumber, numPassenger);
	}

	public String toString() {
		return this.state;
	}
}