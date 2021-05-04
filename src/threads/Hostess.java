package threads;

import sharedRegions.DepartureAirport.DepartureAirport;
import sharedRegions.DepartureAirport.IHostessDP;
import sharedRegions.Repository.IRepository;

public class Hostess extends Thread {

    private int totalNumberOfPassengers;
    private int attendedPassengers;

    //References to shared regions
    IHostessDP departureAirport;

    // Information Repository
    IRepository repository;

    public Hostess(int totalNumberOfPassengers, IHostessDP departureAirport, IRepository repository) {
        this.totalNumberOfPassengers = totalNumberOfPassengers;
        this.departureAirport = departureAirport;
        this.attendedPassengers = 0;
    }

    @Override
    public void run() {

        while(!allPassengersAttended()){
            boolean flight_full = false;
            
            departureAirport.waitingForNextFlight();
            while(!flight_full) {
                departureAirport.waitingForPassenger();
                departureAirport.askForDocuments();
                departureAirport.waitingToCheckPassenger();
                attendedPassengers++;
                flight_full = departureAirport.informReadyToFly();
            }
        }
    }

    public boolean allPassengersAttended(){
        if(attendedPassengers == totalNumberOfPassengers)
            return true;
        else
            return false;
    }
}
