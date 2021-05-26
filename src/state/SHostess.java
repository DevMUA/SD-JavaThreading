package state;

/**
 * The enum S hostess.
 */
public enum SHostess {
	/**
	 * Waiting for flight s hostess.
	 */
	WAITING_FOR_FLIGHT("WTFL", ""),
	/**
	 * Waiting for passenger s hostess.
	 */
	WAITING_FOR_PASSENGER("WTPS", ""),
	/**
	 * The Check passenger.
	 */
	CHECK_PASSENGER("CKPS", "\nFlight %d: passenger %d checked.\n"),
	/**
	 * The Ready to fly.
	 */
	READY_TO_FLY("RDTF", "\nFlight %d: departed with %d passengers.\n")
	;

	private SHostess(String state, String logDiv) { 
		this.state = state; 
		this.logDiv = logDiv;
	}

	private final String state;
	private final String logDiv;

	/**
	 * Log div string.
	 *
	 * @param flightNumber the flight number
	 * @param numPassenger the num passenger
	 * @return the string
	 */
	public String logDiv(int flightNumber, int numPassenger) {
		return String.format(logDiv, flightNumber, numPassenger);
	}

	@Override public String toString() {
		return this.state;
	}
}