package threads;

import sharedRegions.DepartureAirport.IHostessDP;
import sharedRegions.Repository.IRepository;

/**
 * The type Hostess.
 */
public class Hostess extends Thread {

    private final int totalNumberOfPassengers;
    private int attendedPassengers;

    //References to shared regions
    private final IHostessDP departureAirport;

    // Information Repository
    private final IRepository repository;

    /**
     * Instantiates a new Hostess.
     *
     * @param totalNumberOfPassengers the total number of passengers
     * @param departureAirport        the departure airport
     * @param repository              the repository
     */
    public Hostess(int totalNumberOfPassengers, IHostessDP departureAirport, IRepository repository) {
        this.totalNumberOfPassengers = totalNumberOfPassengers;
        this.departureAirport = departureAirport;
        this.repository = repository;
        this.attendedPassengers = 0;
    }

    @Override
    public void run() {

        while(!allPassengersAttended()) {
            boolean flightFull = false;

            departureAirport.waitingForNextFlight();

            while(!flightFull) {
                departureAirport.waitingForPassenger();
                departureAirport.askForDocuments();
                departureAirport.waitingToCheckPassenger();
                attendedPassengers++;
                flightFull = departureAirport.informReadyToFly();
            }
        }
    }

    /**
     * All passengers attended boolean.
     *
     * @return the boolean
     */
    public boolean allPassengersAttended(){
        return attendedPassengers == totalNumberOfPassengers;
    }
}
