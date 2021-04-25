package mainProgram;

import sharedRegions.ArrivalAirport.ArrivalAirport;
import sharedRegions.DepartureAirport.DepartureAirport;
import sharedRegions.Plane.Plane;
import sharedRegions.Repository.Repository;

import threads.Hostess;
import threads.Passenger;
import threads.Pilot;

public class Airlift {

    private static final int N = 21;
    private static final int MIN = 5;
    private static final int MAX = 10;

    public static void main(String args[]){

        //Initializing shared regions
        Repository repository = new Repository(N); 
        
        DepartureAirport departureAirport = new DepartureAirport(MIN, MAX, repository);
        Plane plane = new Plane(repository);
        ArrivalAirport arrivalAirport = new ArrivalAirport(repository);

        //Creating Passenger thread array and pilot/hostess objects
        Passenger[] passengers = new Passenger[N];
        Pilot pilot = new Pilot(departureAirport, plane, repository);
        Hostess hostess = new Hostess(N, departureAirport);

        //Initializing threads
        for(int i = 0; i < passengers.length; i++){
            passengers[i] = new Passenger(i, departureAirport, plane,arrivalAirport);
            passengers[i].start();
        }
        pilot.start();
        hostess.start();

        //Joining threads
        for(int i = 0; i < passengers.length; i++){
            try {
                passengers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            pilot.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            hostess.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        repository.sumUp();
    }

}


