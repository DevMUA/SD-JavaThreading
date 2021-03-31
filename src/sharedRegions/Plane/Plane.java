package sharedRegions.Plane;

import sharedRegions.util.GeneralTool;
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

    public Plane() {
        passengerQueue = new LinkedList<Integer>();


        //passenger variables
        this.leaveThePlane = false;
        //pilot variables
        this.announceArrival = false;
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

        passengerQueue.add(p.getPassengerID());
        System.out.println("Passenger " + p.getPassengerID() + " boarded plane");
    }

    //Passenger waits for plane to land at destination
    @Override
    public synchronized void waitForPlaneToLand() {
        System.out.println("Passenger is waiting for plane to land");
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

        passengerQueue.remove(p.getPassengerID());
        System.out.println("Passenger " + p.getPassengerID() + " left plane");
        if(passengerQueue.size() == 0){
            leaveThePlane = true;
            notifyAll();
            System.out.println("Passenger " + p.getPassengerID() + " notified pilot he was the last one");
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
        System.out.println("announced arrival");
        announceArrival = true;
        notifyAll();
    }

    //Pilot waits for deboarding
    @Override
    public synchronized void waitingForDeboarding() {

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
