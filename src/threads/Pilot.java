package threads;

import sharedRegions.DepartureAirport.DepartureAirport;
import sharedRegions.Plane.Plane;
import sharedRegions.Repository.IRepository;
import common.Parameters;

import state.SPilot;

public class Pilot extends Thread {

    //References to shared regions
    DepartureAirport departureAirport;
    Plane plane;

    // Information Repository
    IRepository repository;

    public Pilot(DepartureAirport departureAirport, Plane plane, IRepository repository) {
        this.departureAirport = departureAirport;
        this.plane = plane;

        this.repository = repository;
    }

    @Override
    public void run() {
        while(!departureAirport.isInformPilotToCeaseActivity()) {

            repository.update(SPilot.AT_TRANSFER_GATE);

            departureAirport.informReadyBoarding();
            departureAirport.waitingForBoarding();
            
            //Fly to destination
            repository.update(SPilot.FLYING_FORWARD);
            fly();
            
            plane.announceArrival();
            plane.waitingForDeboarding();
            
            //Fly to origin point
            repository.update(SPilot.FLYING_BACK);
            fly();
        }
    }

    private void fly(){
        try {
            Thread.sleep(Parameters.PLANETRAVELTIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
