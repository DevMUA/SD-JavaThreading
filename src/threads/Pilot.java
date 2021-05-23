package threads;

import common.ServerInformation;
import sharedRegions.DepartureAirport.IPilotDP;
import sharedRegions.Plane.IPilotP;
import sharedRegions.Repository.IRepository;

import state.SPilot;

public class Pilot extends Thread {

    //References to shared regions
    private final IPilotDP departureAirport;
    private final IPilotP plane;

    // Information Repository
    private final IRepository repository;

    public Pilot(IPilotDP departureAirport, IPilotP plane, IRepository repository) {
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
            Thread.sleep(ServerInformation.PLANETRAVELTIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
