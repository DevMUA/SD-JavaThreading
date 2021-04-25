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
    SPilot sPilot;
    SHostess sHostess;

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
        sPilot = SPilot.AT_TRANSFER_GATE;
        sHostess = SHostess.WAITING_FOR_FLIGHT;

        String state = String.format("%"+ 42 +"sAirlift - Description of the internal state\n\n PT   HT ", " ");

        for(int i = 0; i < NUMBER_PASSENGERS; i++)
            state += String.format("  P%02d", i);
        
        state += " InQ InF PTAL\n";
        
        write(state);
	}
	
    public synchronized void update(SPilot state) {
        sPilot = state;
        String info;

        switch(state) {
            case READY_FOR_BOARDING:      
                info = String.format("\nFlight %d: boarding started.\n", flights.size());
                write(info);
                break;

            case DEBOARDING:
                info = String.format("\nFlight %d: arrived.\n", flights.size());
                write(info);
                break;

            case FLYING_BACK:
                info = String.format("\nFlight %d: returning.\n", flights.size());
                write(info);
                break;
        }
        //write(state + "\n");
        write(state());
    }

    public synchronized void update(int passenger_no, SHostess state) {
        String info = String.format("\nFlight %d: passenger %d checked\n", flights.size(), passenger_no);
        write(info);
        inq--;

        sHostess = state;
        //write(state + "\n");
        write(state());
    }

    public synchronized void update(SHostess state) {
        if(sHostess != state) {
            switch (state) {
                case READY_TO_FLY:
                flights.addLast(inf);
                String info = String.format("\nFlight %d: departed with %d passengers.\n", flights.size(), inf);
                write(info);
            }

            sHostess = state;
            //write(state + "\n");
            write(state());
        }
    }

    public synchronized void update(int passenger_no, SPassenger state) {
        sPassengers[passenger_no] = state;
        switch(state) {
            
            case IN_QUEUE:
                inq++;
                break;
            
            case IN_FLIGHT:
                inf++;
                break;
            
            case AT_DESTINATION:
                inf--; ptal++;
        }
        
        //write(state + "\n");
        write(state());
    }

    public synchronized void sumUp() {
        String info = "\nAirlift sum up:\n";
        for(int i = 0; i < flights.size(); i++) {
            info += String.format("Flight %d transported %d passengers\n", i+1, flights.get(i));
        }
        write(info + ".");
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
