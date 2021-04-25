package threads;

import sharedRegions.ArrivalAirport.ArrivalAirport;
import sharedRegions.DepartureAirport.DepartureAirport;
import sharedRegions.Plane.Plane;
import sharedRegions.util.GeneralTool;

public class Passenger extends Thread {

    //Private attributes
    private int passengerID;

    //References to shared regions
    DepartureAirport departureAirport;
    Plane plane;
    ArrivalAirport arrivalAirport;

    public Passenger(int passengerID, DepartureAirport departureAirport, Plane plane, ArrivalAirport arrivalAirport) {
        this.passengerID = passengerID;
        this.departureAirport = departureAirport;
        this.plane = plane;
        this.arrivalAirport = arrivalAirport;
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
    private void goingToAirport(){
        int randomSleepValue = GeneralTool.getRandomNumber(5,25);

        try {
            Thread.sleep(randomSleepValue*1000);
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
