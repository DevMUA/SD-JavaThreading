package sharedRegions.DepartureAirport;

/**
 * The interface Hostess dp.
 */
public interface IHostessDP {
    /**
     * Waiting for next flight int.
     *
     * @return the int
     */
    public int waitingForNextFlight();

    /**
     * Waiting for passenger int.
     *
     * @return the int
     */
    public int waitingForPassenger();

    /**
     * Ask for documents int.
     *
     * @return the int
     */
    public int askForDocuments();

    /**
     * Waiting to check passenger int.
     *
     * @return the int
     */
    public int waitingToCheckPassenger();

    /**
     * Inform ready to fly boolean.
     *
     * @return the boolean
     */
    public boolean informReadyToFly();
}
