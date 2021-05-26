package sharedRegions.DepartureAirport;

/**
 * The interface Pilot dp.
 */
public interface IPilotDP {
    /**
     * Inform ready boarding int.
     *
     * @return the int
     */
    public int informReadyBoarding();

    /**
     * Waiting for boarding int.
     *
     * @return the int
     */
    public int waitingForBoarding();

    /**
     * Is inform pilot to cease activity boolean.
     *
     * @return the boolean
     */
    public boolean isInformPilotToCeaseActivity();
}
