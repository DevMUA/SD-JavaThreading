package state;

/**
 * The enum S pilot.
 */
public enum SPilot {
	/**
	 * At transfer gate s pilot.
	 */
	AT_TRANSFER_GATE("ATRG", ""),
	/**
	 * The Ready for boarding.
	 */
	READY_FOR_BOARDING("RDFB", "\nFlight %d: boarding started.\n"),
	/**
	 * Waiting for boarding s pilot.
	 */
	WAITING_FOR_BOARDING("WTFB", ""),
	/**
	 * Flying forward s pilot.
	 */
	FLYING_FORWARD("FLFW", ""),
	/**
	 * Deboarding s pilot.
	 */
	DEBOARDING("DRPP", "\nFlight %d: arrived.\n"),
	/**
	 * Flying back s pilot.
	 */
	FLYING_BACK("FLBK", "\nFlight %d: returning.\n")
	;

	private SPilot(String state, String logDiv) { 
		this.state = state; 
		this.logDiv = logDiv;
	}
	private final String state;
	private final String logDiv;

	/**
	 * Log div string.
	 *
	 * @param flightNumber the flight number
	 * @return the string
	 */
	public String logDiv(int flightNumber) {
		return String.format(logDiv, flightNumber);
	}

	@Override public String toString() {
		return this.state;
	}
}
