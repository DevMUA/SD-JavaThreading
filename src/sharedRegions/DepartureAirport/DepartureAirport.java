package sharedRegions.DepartureAirport;

import sharedRegions.util.GeneralTool;
import threads.Hostess;
import threads.Passenger;

import java.util.LinkedList;
import java.util.Queue;

public class DepartureAirport implements IHostessDP,IPassengerDP,IPilotDP{

    //Passenger list
    private Queue<Integer> passengerQueue;

    //Passengers that were allowed in the plane already
    private int passengersInPlane;

    //Maximum and minimum passengers per plane
    private int MIN;
    private int MAX;

    //Signaling variables passenger
    private boolean showingDocuments;
    private boolean checkedIn;
    //Signaling variables hostess
    private boolean waitForNextPassenger;
    private boolean askDocuments;
    private boolean informPlaneReadyToTakeOff;
    private boolean informPilotToCeaseActivity;
    //Signaling variables pilot
    private boolean informPlaneReadyForBoarding;

    public DepartureAirport(int MIN,int MAX) {
        passengerQueue = new LinkedList<Integer>();
        passengersInPlane = 0;

        this.MIN = MIN;
        this.MAX = MAX;

        //passenger variables
        showingDocuments = false;
        checkedIn = false;
        //hostess variables
        waitForNextPassenger = true;
        askDocuments = false;
        informPlaneReadyToTakeOff = false;
        informPilotToCeaseActivity = false;
        //pilot variables
        informPlaneReadyForBoarding = false;
    }

    /*
         _               _
        | |             | |
        | |__   ___  ___| |_ ___  ___ ___
        | '_ \ / _ \/ __| __/ _ \/ __/ __|
        | | | | (_) \__ \ ||  __/\__ \__ \
        |_| |_|\___/|___/\__\___||___/___/
         */

    //Hostess waits for the plane to be ready for boarding
    @Override
    public synchronized void waitingForNextFlight() {
        while(!informPlaneReadyForBoarding){
            System.out.println("Hostess is waiting for the flight to be ready to be boarded");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //hostess removes one person from the queue and notifies all passenger (so they check if they were the one removed)
    @Override
    public synchronized void waitingForPassenger() {
        waitForNextPassenger = false;
        notifyAll();
        int nextPassenger;
        while(passengerQueue.size() == 0){
            System.out.println("Hostess is waiting for passengers");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nextPassenger = passengerQueue.remove();
        notifyAll();
        System.out.println("Hostess accepted a passenger for check in");

    }

    //hostess asks for the documents and waits for the passenger to show the documents
    @Override
    public synchronized void askForDocuments() {
        System.out.println("Hostess asks passenger for documents");
        askDocuments = true;
        notifyAll();

    }

    //hostess waits for the passenger to show the documents and then starts waiting for the next passenger and notifies the passenger that he is checked in
    @Override
    public synchronized void waitingToCheckPassenger() {
        while(!showingDocuments){
            System.out.println("Hostess waits for passenger to give documents");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        waitForNextPassenger = true;
        showingDocuments = false;
        System.out.println("Hostess received and accepted documents");
        notifyAll();

        while(!checkedIn){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        checkedIn = false;
    }
    //hostess checks if plane is ready to fly and gives the signal if it is
    @Override
    public synchronized void informReadyToFly() {
        Hostess h = (Hostess) Thread.currentThread();

        // IF ONE OF THESE CONDITIONS ARE MET GIVE THE SIGNAL TO FLY
        // 1. PASSENGERS IN QUEUE ARE 0 AND THE PASSENGERS IN THE PLANE MEET THE MINIMUM REQUIREMENTS
        // 2. PASSENGERS IN PLANE IS ALREADY AT A MAXIMUM
        // 3. PASSENGERS IN QUEUE ARE 0 AND HOSTESS KNOWS THOSE WERE THE LAST ONES
        if((passengerQueue.size() == 0 && passengersInPlane > MIN) || passengersInPlane == MAX || (passengerQueue.size() == 0 && h.allPassengersAttended())){
            informPlaneReadyToTakeOff = true;
            if(h.allPassengersAttended()){
                informPilotToCeaseActivity = true;
                System.out.println("Hostess informs pilot that he can cease activity");
            }
            notifyAll();
            System.out.println("Hostess informs plane is ready to fly");

            while(informPlaneReadyForBoarding){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            passengersInPlane = 0;
            informPlaneReadyToTakeOff = false;
        }
    }

    /*
     _ __   __ _ ___ ___  ___ _ __   __ _  ___ _ __
    | '_ \ / _` / __/ __|/ _ \ '_ \ / _` |/ _ \ '__|
    | |_) | (_| \__ \__ \  __/ | | | (_| |  __/ |
    | .__/ \__,_|___/___/\___|_| |_|\__, |\___|_|
    | |                              __/ |
    |_|                             |___/
     */

    // Passenger places itself in queue and notifies hostess
    @Override
    public synchronized void travelToAirport() {
        Passenger p = (Passenger) Thread.currentThread();

        passengerQueue.add(p.getPassengerID());
        notifyAll();

        System.out.println("Passenger " + p.getPassengerID() + " got to the airport");
    }

    //Passenger waits in queue until he is removed from said queue
    @Override
    public synchronized void waitInQueue() {
        Passenger p = (Passenger) Thread.currentThread();
        System.out.println("Passenger " + p.getPassengerID() + " is waiting in queue");
        while(passengerQueue.contains(p.getPassengerID())){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Passenger showsDocuments and notifies hostess
    @Override
    public synchronized void showDocuments() {
        System.out.println("Passenger is being asked for documents");
        while(!askDocuments){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        showingDocuments = true;
        notifyAll();
        System.out.println("Passenger showed documents");
    }

    //Passenger waits for hostess to be in waitForNextPassenger state then enters the plane and increments passengers in plane
    @Override
    public synchronized void waitingToBeCheckedIn() {
        System.out.println("Passenger waiting to be checked in");
        while(!waitForNextPassenger){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        checkedIn = true;
        passengersInPlane++;
        System.out.println("Passenger was checked in");
        notifyAll();
    }

    /*
           _ _       _
          (_) |     | |
     _ __  _| | ___ | |_
    | '_ \| | |/ _ \| __|
    | |_) | | | (_) | |_
    | .__/|_|_|\___/ \__|
    | |
    |_|
     */

    //Pilot informs hostess that plane is ready for boarding
    @Override
    public synchronized void informReadyBoarding() {
        System.out.println("Pilot informed plane is ready to be boarded");
        informPlaneReadyForBoarding = true;
        notifyAll();
    }

    //Pilot waits until hostess gives signal that plane is ready for boarding and if it is signals that there is no more boarding
    @Override
    public synchronized void waitingForBoarding() {
        System.out.println("Pilot is waiting for the boarding to be finished");
        while(!informPlaneReadyToTakeOff){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        informPlaneReadyForBoarding = false;
        notifyAll();
        System.out.println("Boarding is finished and pilot is going to fly");
    }

    public synchronized boolean isInformPilotToCeaseActivity() {
        return informPilotToCeaseActivity;
    }

    public synchronized void setInformPilotToCeaseActivity(boolean informPilotToCeaseActivity) {
        this.informPilotToCeaseActivity = informPilotToCeaseActivity;
    }
}
