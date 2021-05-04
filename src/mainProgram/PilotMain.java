package mainProgram;

import common.ServerInformation;
import stubs.DepartureAirportStub;
import stubs.PlaneStub;
import sharedRegions.DepartureAirport.IPilotDP;
import sharedRegions.Plane.IPilotP;
import threads.Pilot;


public class PilotMain {

    IPilotDP departureAirport = new DepartureAirportStub(ServerInformation.DEPARTUREAIRPORTHOSTNAME, ServerInformation.DEPARTUREAIRPORTSERVERPORT);
    IPilotP plane = new PlaneStub(ServerInformation.PLANEHOSTNAME, ServerInformation.PLANESERVERPORT);

    //RepositoryStub repository = new RepositoryStub();

    Pilot pilot = new Pilot(departureAirport, plane, repository);

    pilot.start();
    try{
        pilot.join();
    } catch(InterruptedException e) {
        e.printStackTrace();
    }
}
