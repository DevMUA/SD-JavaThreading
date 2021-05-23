package threads;

import sharedRegions.DepartureAirport.IHostessDP;
import sharedRegions.Repository.IRepository;

public class Hostess extends Thread {

    private final int totalNumberOfPassengers;
    private int attendedPassengers;

    //References to shared regions
    private final IHostessDP departureAirport;

    // Information Repository
    private final IRepository repository;

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

    public boolean allPassengersAttended(){
        return attendedPassengers == totalNumberOfPassengers;
    }
}
