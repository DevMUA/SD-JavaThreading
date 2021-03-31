package threads;

import sharedRegions.DepartureAirport.DepartureAirport;
import sharedRegions.Plane.Plane;
import sharedRegions.util.GeneralTool;

public class Pilot extends Thread{

    //References to shared regions
    DepartureAirport departureAirport;
    Plane plane;

    public Pilot(DepartureAirport departureAirport, Plane plane) {
        this.departureAirport = departureAirport;
        this.plane = plane;
    }

    @Override
    public void run() {

        while(true){
            if(departureAirport.isInformPilotToCeaseActivity())
                break;
            departureAirport.informReadyBoarding();
            departureAirport.waitingForBoarding();
            //Fly to destination
            fly();
            plane.announceArrival();
            plane.waitingForDeboarding();
            //Fly to origin point
            fly();
        }

        System.out.println("Pilot ceased activity");
    }

    private void fly(){
        int randomSleepValue = GeneralTool.getRandomNumber(5,20);

        try {
            Thread.sleep(randomSleepValue*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
