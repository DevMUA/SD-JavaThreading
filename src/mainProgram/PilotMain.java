package mainProgram;

import common.ServerInformation;
import stubs.DepartureAirportStub;
import stubs.PlaneStub;
import stubs.RepositoryStub;
import sharedRegions.DepartureAirport.IPilotDP;
import sharedRegions.Plane.IPilotP;
import sharedRegions.Repository.IRepository;
import threads.Pilot;

public class PilotMain {

    public static void main(String args[]) {
        IPilotDP departureAirport = new DepartureAirportStub(ServerInformation.DEPARTUREAIRPORTHOSTNAME, ServerInformation.DEPARTUREAIRPORTSERVERPORT);
        IPilotP plane = new PlaneStub(ServerInformation.PLANEHOSTNAME, ServerInformation.PLANESERVERPORT);

        IRepository repository = new RepositoryStub(ServerInformation.REPOSITORYHOSTNAME, ServerInformation.REPOSITORYPORT);

        Pilot pilot = new Pilot(departureAirport, plane, repository);
        pilot.start();
        try {
            pilot.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}