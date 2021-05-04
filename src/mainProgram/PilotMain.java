package mainProgram;

import stubs.DepartureAirportStub;
import stubs.PlaneStub;
import stubs.RepositoryStub;
import sharedRegions.DepartureAirport.IPilotDP;
import sharedRegions.Plane.IPilotP;
import threads.Pilot;
import common.Parameters;

public class PilotMain {

    IPilotDP departureAirport = new DepartureAirportStub(Parameters.DEPARTUREAIRPORTHOSTNAME, Parameters.DEPARTUREAIRPORTPORT);
    IPilotP plane = new PlaneStub(Parameters.PLANEHOSTNAME, Parameters.PLANEPORT);

    //RepositoryStub repository = new RepositoryStub();

    Pilot pilot = new Pilot(departureAirport, plane, repository);

    pilot.start();
    try{
        pilot.join();
    } catch(InterruptedException e) {
        e.printStackTrace();
    }
}
