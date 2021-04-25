package state;

public enum SHostess {
	WAITING_FOR_FLIGHT("WTFL"),
	WAITING_FOR_PASSENGER("WTPS"),
	CHECK_PASSENGER("CKPS"),
	READY_TO_FLY("RDTF")
	;

	private SHostess(String state) { this.state = state; }
	private final String state;

	public String toString() {
		return this.state;
	}
}