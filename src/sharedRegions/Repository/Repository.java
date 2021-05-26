package sharedRegions.Repository;

import state.SPilot;
import state.SHostess;
import state.SPassenger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * The type Repository.
 */
public class Repository implements IRepository {

    /**
     * The S passengers.
     */
    SPassenger[] sPassengers;
    /**
     * The S pilot.
     */
    SPilot sPilot = SPilot.AT_TRANSFER_GATE;
    /**
     * The S hostess.
     */
    SHostess sHostess = SHostess.WAITING_FOR_FLIGHT;

    /**
     * The Flights.
     */
// Sums up of passengers transported in all the flights
    LinkedList<Integer> flights = new LinkedList<>();

    /**
     * The Inq.
     */
// number of passengers presently forming a queue to board the plane
    int inq = 0;
    /**
     * The Inf.
     */
// number of passengers in the plane
    int inf = 0;
    /**
     * The Ptal.
     */
// number of passengers that have already arrived at their destination
    int ptal = 0;

    /**
     * Instantiates a new Repository.
     *
     * @param NUMBER_PASSENGERS the number passengers
     */
    public Repository(int NUMBER_PASSENGERS) {
	   	sPassengers = new SPassenger[NUMBER_PASSENGERS];
        Arrays.fill(sPassengers, SPassenger.GOING_TO_AIRPORT);

        String state = String.format("%"+ 42 +"sAirlift - Description of the internal state\n\n PT   HT ", " ");

        for(int i = 0; i < NUMBER_PASSENGERS; i++)
            state += String.format("  P%02d", i);

        state += " InQ InF PTAL\n";
        write(state);
	}
	
    public synchronized int update(SPilot state) {
        sPilot = state;

        if(state==SPilot.READY_FOR_BOARDING) 
                flights.addLast(0);

        String div = state.logDiv(flights.size());
        write(div + state());

        return 0;
    }

    public synchronized int update(int passengerID, SHostess state) {
        sHostess = state;

        String div = state.logDiv(flights.size(), passengerID);
        write(div + state());

        return 0;
    }

    public synchronized int update(SHostess state) {
        if(sHostess != state) {
            sHostess = state;
            
            if(state==SHostess.READY_TO_FLY)  {
                flights.removeLast();
                flights.addLast(inf);
            }
            
            String div = state.logDiv(flights.size(), inf);
            write(div + state());
        }

        return 0;
    }

    public synchronized int update(int passengerID, SPassenger state) {
        sPassengers[passengerID] = state;

        switch(state) {
            case AT_DESTINATION:
                ptal++;
        }

        write(state());
        return 0;
    }

    public synchronized int updateInq(int inq)   { this.inq = inq;   return 0; }
    public synchronized int updateInf(int inf)   { this.inf = inf;   return 0; }
    public synchronized int updatePtal(int ptal) { this.ptal = ptal; return 0; }

    public synchronized int writeSumUp() {
        StringBuilder info = new StringBuilder("\nAirlift sum up:");
        
        for(int i = 0; i < flights.size(); i++) {
            info.append(String.format("%nFlight %d transported %d passengers", i + 1, flights.get(i)));
        }
        
        write(info + ".\n");
        return 0;
    }

    // Obtaining last updated state of every thread
    private String state() {
        String state = sPilot.toString() + " " + sHostess.toString();
        for(SPassenger passenger : sPassengers)
            state += " " + passenger.toString();
        return state + String.format("  %2d  %2d  %2d %n", inq, inf, ptal);
    }

    private void write(String lines) {
        //System.out.print(lines);
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
