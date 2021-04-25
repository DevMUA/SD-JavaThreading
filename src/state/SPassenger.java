package state;

public enum SPassenger {
	GOING_TO_AIRPORT("GTAP"),
	IN_QUEUE("INQE"),
	IN_FLIGHT("INFL"),
	AT_DESTINATION("ATDS"),
	;

	private SPassenger(String state) { this.state = state; }
	private final String state;

	public String toString() {
		return this.state;
	}
}