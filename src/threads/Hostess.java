package threads;

import sharedRegions.DepartureAirport.DepartureAirport;

public class Hostess extends Thread{

    private int totalNumberOfPassengers;
    private int attendedPassengers;

    //References to shared regions
    DepartureAirport departureAirport;

    public Hostess(int totalNumberOfPassengers, DepartureAirport departureAirport) {
        this.totalNumberOfPassengers = totalNumberOfPassengers;
        this.departureAirport = departureAirport;
        this.attendedPassengers = 0;
    }

    @Override
    public void run() {

        while(!allPassengersAttended()){
            departureAirport.waitingForNextFlight();
            departureAirport.waitingForPassenger();
            departureAirport.askForDocuments();
            departureAirport.waitingToCheckPassenger();
            attendedPassengers++;
            departureAirport.informReadyToFly();
        }

        System.out.println("Hostess ceased activity");
    }

    public boolean allPassengersAttended(){
        if(attendedPassengers == totalNumberOfPassengers)
            return true;
        else
            return false;
    }
}
