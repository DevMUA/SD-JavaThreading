package threads;

import common.ServerInformation;
import sharedRegions.ArrivalAirport.IPassengerAR;
import sharedRegions.DepartureAirport.IPassengerDP;
import sharedRegions.Plane.IPassengerP;
import sharedRegions.Repository.IRepository;

public class Passenger extends Thread {

    //Private attributes
    private int passengerID;

    //References to shared regions
    private IPassengerDP departureAirport;
    private IPassengerP plane;
    private IPassengerAR arrivalAirport;

    // Information Repository
    IRepository repository;

    public Passenger(int passengerID, IPassengerDP departureAirport, IPassengerP plane, IPassengerAR arrivalAirport, IRepository repository) {
        this.passengerID = passengerID;
        this.departureAirport = departureAirport;
        this.plane = plane;
        this.arrivalAirport = arrivalAirport;
        this.repository = repository;
    }

    // PASSENGER LIFECYCLE
    @Override
    public void run() {

        //In departure airport
        goingToAirport();
        departureAirport.travelToAirport();
        departureAirport.waitInQueue();
        departureAirport.showDocuments();
        departureAirport.waitingToBeCheckedIn();
        //In plane
        plane.boardPlane();
        plane.waitForPlaneToLand();
        plane.leavePlane();

        //In arrival airport
        arrivalAirport.leaveAirport();
    }

    //Makes thread sleep for a random time between 5 and 20
    private void goingToAirport() {
        try {
            Thread.sleep(ServerInformation.AIRPORTTRAVELTIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }
}
