package sharedRegions.DepartureAirport;

import common.HostessInterface;
import common.PassengerInterface;
import common.ServiceProvider;
import sharedRegions.Repository.IRepository;

import state.SPilot;
import state.SHostess;
import state.SPassenger;

import threads.Hostess;
import threads.Passenger;

import java.util.LinkedList;
import java.util.Queue;

public class DepartureAirport implements IHostessDP, IPassengerDP, IPilotDP {

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

    // Information Repository
    IRepository repository;

    public DepartureAirport(int MIN,int MAX, IRepository repository) {
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

        this.repository = repository;
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
    public synchronized int waitingForNextFlight() {
        //repository.update(SHostess.WAITING_FOR_FLIGHT);
        
        while(!informPlaneReadyForBoarding){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    //hostess removes one person from the queue and notifies all passenger (so they check if they were the one removed)
    @Override
    public synchronized int waitingForPassenger() {
        //repository.update(SHostess.WAITING_FOR_PASSENGER);
        
        waitForNextPassenger = false;
        notifyAll();
        while(passengerQueue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        int nextPassenger = passengerQueue.remove();
        
        //repository.updateInq(passengerQueue.size());
        //repository.update(nextPassenger, SHostess.CHECK_PASSENGER);
        
        notifyAll();
        return 0;
    }

    //hostess asks for the documents and waits for the passenger to show the documents
    @Override
    public synchronized int askForDocuments() {
        askDocuments = true;
        notifyAll();
        return 0;
    }

    //hostess waits for the passenger to show the documents and then starts waiting for the next passenger and notifies the passenger that he is checked in
    @Override
    public synchronized int waitingToCheckPassenger() {
        while(!showingDocuments){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitForNextPassenger = true;
        showingDocuments = false;
        notifyAll();

        repository.update(SHostess.WAITING_FOR_PASSENGER);

        while(!checkedIn){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        checkedIn = false;
        return 0;
    }

    //hostess checks if plane is ready to fly and gives the signal if it is
    @Override
    public synchronized boolean informReadyToFly() {
        HostessInterface h = (ServiceProvider) Thread.currentThread();

        // IF ONE OF THESE CONDITIONS ARE MET GIVE THE SIGNAL TO FLY
        // 1. PASSENGERS IN QUEUE ARE 0 AND THE PASSENGERS IN THE PLANE MEET THE MINIMUM REQUIREMENTS
        // 2. PASSENGERS IN PLANE IS ALREADY AT A MAXIMUM
        // 3. PASSENGERS IN QUEUE ARE 0 AND HOSTESS KNOWS THOSE WERE THE LAST ONES
        if((passengerQueue.size() == 0 && passengersInPlane > MIN) || passengersInPlane == MAX || (passengerQueue.size() == 0 && h.allPassengersAttended())){

            //repository.update(SHostess.READY_TO_FLY);
            
            informPlaneReadyToTakeOff = true;
            if(h.allPassengersAttended()){
                informPilotToCeaseActivity = true;
            }
            notifyAll();

            while(informPlaneReadyForBoarding){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            passengersInPlane = 0;
            informPlaneReadyToTakeOff = false;

            return true;
        }
        return false;
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
    public synchronized int travelToAirport() {
        PassengerInterface p = (ServiceProvider) Thread.currentThread();
        int passengerID = p.getPassengerID();

        passengerQueue.add(passengerID);
        
        //repository.updateInq(passengerQueue.size());
        //repository.update(passengerID, SPassenger.IN_QUEUE);

        notifyAll();
        return 0;
    }

    //Passenger waits in queue until he is removed from said queue
    @Override
    public synchronized int waitInQueue() {
        PassengerInterface p = (ServiceProvider) Thread.currentThread();
        int passengerID = p.getPassengerID();

        while(passengerQueue.contains(passengerID)){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    //Passenger showsDocuments and notifies hostess
    @Override
    public synchronized int showDocuments() {
        while(!askDocuments){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        showingDocuments = true;
        notifyAll();
        return 0;
    }

    //Passenger waits for hostess to be in waitForNextPassenger state then enters the plane and increments passengers in plane
    @Override
    public synchronized int waitingToBeCheckedIn() {
        while(!waitForNextPassenger){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        checkedIn = true;
        passengersInPlane++;
        notifyAll();
        return 0;
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
    public synchronized int informReadyBoarding() {
        //repository.update(SPilot.READY_FOR_BOARDING);
        
        informPlaneReadyForBoarding = true;
        notifyAll();
        return 0;
    }

    //Pilot waits until hostess gives signal that plane is ready for boarding and if it is signals that there is no more boarding
    @Override
    public synchronized int waitingForBoarding() {
        //repository.update(SPilot.WAITING_FOR_BOARDING);
        
        while(!informPlaneReadyToTakeOff){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        informPlaneReadyForBoarding = false;
        notifyAll();
        return 0;
    }

    public synchronized boolean isInformPilotToCeaseActivity() {
        return informPilotToCeaseActivity;
    }

    public synchronized void setInformPilotToCeaseActivity(boolean informPilotToCeaseActivity) {
        this.informPilotToCeaseActivity = informPilotToCeaseActivity;
    }
}
