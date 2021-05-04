package sharedRegions.DepartureAirport;

public interface IPilotDP {
    public int informReadyBoarding();
    public int waitingForBoarding();
    public boolean isInformPilotToCeaseActivity();
}
