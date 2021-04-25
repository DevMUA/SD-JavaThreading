package state;

public enum SPilot {
	AT_TRANSFER_GATE("ATRG"),
	READY_FOR_BOARDING("RDFB"),
	WAITING_FOR_BOARDING("WTFB"),
	FLYING_FORWARD("FLFW"),
	DEBOARDING("DRPP"),
	FLYING_BACK("FLBK")
	;

	private SPilot(String state) { this.state = state; }
	private final String state;

	public String toString() {
		return this.state;
	}
}
