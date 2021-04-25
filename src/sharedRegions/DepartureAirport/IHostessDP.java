package sharedRegions.DepartureAirport;

public interface IHostessDP {
    public void waitingForNextFlight();
    public void waitingForPassenger();
    public void askForDocuments();
    public void waitingToCheckPassenger();
    public boolean informReadyToFly();
}
