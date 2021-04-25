package sharedRegions.Plane;

import sharedRegions.Repository.IRepository;
import sharedRegions.util.GeneralTool;

import state.SPilot;
import state.SPassenger;

import threads.Passenger;

import java.util.LinkedList;
import java.util.Queue;

public class Plane implements IPassengerP,IPilotP {

    //Passenger list
    private Queue<Integer> passengerQueue;

    //Signaling variables passenger
    private boolean leaveThePlane;
    //Signaling variables pilot
    private boolean announceArrival;

    // Information Repository
    IRepository repository;

    public Plane(IRepository repository) {
        passengerQueue = new LinkedList<Integer>();


        //passenger variables
        this.leaveThePlane = false;
        //pilot variables
        this.announceArrival = false;

        this.repository = repository;
    }

    /*
     _ __   __ _ ___ ___  ___ _ __   __ _  ___ _ __
    | '_ \ / _` / __/ __|/ _ \ '_ \ / _` |/ _ \ '__|
    | |_) | (_| \__ \__ \  __/ | | | (_| |  __/ |
    | .__/ \__,_|___/___/\___|_| |_|\__, |\___|_|
    | |                              __/ |
    |_|                             |___/
    */

    //Passenger enters the plane list
    @Override
    public synchronized void boardPlane() {
        Passenger p = (Passenger) Thread.currentThread();
        int passengerID = p.getPassengerID();

        repository.update(passengerID, SPassenger.IN_FLIGHT);

        passengerQueue.add(p.getPassengerID());
    }

    //Passenger waits for plane to land at destination
    @Override
    public synchronized void waitForPlaneToLand() {
        while(!announceArrival){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Passenger leaves the plane and if he is the last notifies the pilot
    @Override
    public synchronized void leavePlane() {
        Passenger p = (Passenger) Thread.currentThread();
        int passengerID = p.getPassengerID();
        
        passengerQueue.remove(passengerID);

        repository.update(passengerID, SPassenger.AT_DESTINATION);

        if(passengerQueue.size() == 0){
            leaveThePlane = true;
            notifyAll();
        }
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

    //Pilot announces arrival to passengers
    @Override
    public synchronized void announceArrival() {
        announceArrival = true;
        notifyAll();
    }

    //Pilot waits for deboarding
    @Override
    public synchronized void waitingForDeboarding() {
        repository.update(SPilot.DEBOARDING);
        
        while(!leaveThePlane){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        announceArrival = false;
        leaveThePlane = false;
        notifyAll();
    }

}
