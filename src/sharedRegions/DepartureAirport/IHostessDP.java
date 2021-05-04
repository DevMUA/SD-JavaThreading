package sharedRegions.DepartureAirport;

public interface IHostessDP {
    public int waitingForNextFlight();
    public int waitingForPassenger();
    public int askForDocuments();
    public int waitingToCheckPassenger();
    public boolean informReadyToFly();
}
