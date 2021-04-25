package sharedRegions.Repository;

import state.SPilot;
import state.SHostess;
import state.SPassenger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.LinkedList;

public class Repository implements IRepository {

	SPassenger sPassengers[]; 
    SPilot sPilot = SPilot.AT_TRANSFER_GATE;
    SHostess sHostess = SHostess.WAITING_FOR_FLIGHT;

    // Sums up of passengers transported in all the flights
    LinkedList<Integer> flights = new LinkedList<Integer>();

    // number of passengers presently forming a queue to board the plane
    int inq = 0;
    // number of passengers in the plane
    int inf = 0;
    // number of passengers that have already arrived at their destination
    int ptal = 0;

	public Repository(int NUMBER_PASSENGERS) {
	   	sPassengers = new SPassenger[NUMBER_PASSENGERS];
        Arrays.fill(sPassengers, SPassenger.GOING_TO_AIRPORT);

        String state = String.format("%"+ 42 +"sAirlift - Description of the internal state\n\n PT   HT ", " ");
        for(int i = 0; i < NUMBER_PASSENGERS; i++)
            state += String.format("  P%02d", i);
        state += " InQ InF PTAL\n";

        write(state);
	}
	
    public synchronized void update(SPilot state) {
        sPilot = state;

        if(state==SPilot.READY_FOR_BOARDING) 
                flights.addLast(0);

        String div = state.logDiv(flights.size());
        write(div + state());
    }

    public synchronized void update(int passenger_no, SHostess state) {
        sHostess = state;

        String div = state.logDiv(flights.size(), passenger_no);    
        write(div + state());
    }

    public synchronized void update(SHostess state) {
        if(sHostess != state) {
            sHostess = state;
            
            if(state==SHostess.READY_TO_FLY)  {
                flights.removeLast();
                flights.addLast(inf);
            }
            
            String div = state.logDiv(flights.size(), inf);
            write(div + state());
        }
    }

    public synchronized void update(int passenger_no, SPassenger state) {
        sPassengers[passenger_no] = state;
        switch(state) {
            case AT_DESTINATION:
                ptal++;
        }
        write(state());
    }

    public synchronized void updateInq(int inq) { this.inq = inq; }
    public synchronized void updateInf(int inf) { this.inf = inf; }
    public synchronized void updatePtal(int ptal) { this.ptal = ptal; }

    public synchronized void writeSumUp() {
        String info = "\nAirlift sum up:";
        
        for(int i = 0; i < flights.size(); i++) {
            info += String.format("\nFlight %d transported %d passengers", i+1, flights.get(i));
        }
        
        write(info + ".\n");
    }

    // Obtaining last updated state of every thread;
    private String state() {
        String state = sPilot.toString() + " " + sHostess.toString();
        for(int i = 0; i < sPassengers.length; i++)
            state += " " + sPassengers[i].toString();
        return state + String.format("  %2d  %2d  %2d \n", inq, inf, ptal);
    }

    private void write(String lines) {
        System.out.print(lines);
        try {
            Files.write(
                    Paths.get("log.txt"),
                    lines.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
}
