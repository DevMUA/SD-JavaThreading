package state;

/**
 * The enum S passenger.
 */
public enum SPassenger {
	/**
	 * Going to airport s passenger.
	 */
	GOING_TO_AIRPORT("GTAP"),
	/**
	 * In queue s passenger.
	 */
	IN_QUEUE("INQE"),
	/**
	 * In flight s passenger.
	 */
	IN_FLIGHT("INFL"),
	/**
	 * At destination s passenger.
	 */
	AT_DESTINATION("ATDS"),
	;

	private SPassenger(String state) { this.state = state; }
	private final String state;

	@Override public String toString() {
		return this.state;
	}
}