package state;

public enum SPilot {
	AT_TRANSFER_GATE("ATRG", ""),
	READY_FOR_BOARDING("RDFB", "\nFlight %d: boarding started.\n"),
	WAITING_FOR_BOARDING("WTFB", ""),
	FLYING_FORWARD("FLFW", ""),
	DEBOARDING("DRPP", "\nFlight %d: arrived.\n"),
	FLYING_BACK("FLBK", "\nFlight %d: returning.\n")
	;

	private SPilot(String state, String logDiv) { 
		this.state = state; 
		this.logDiv = logDiv;
	}
	private final String state;
	private final String logDiv;

	public String logDiv(int flightNumber) {
		return String.format(logDiv, flightNumber);
	}

	public String toString() {
		return this.state;
	}
}
