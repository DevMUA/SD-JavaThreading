package mainProgram;

import common.ServerInformation;
import stubs.DepartureAirportStub;
import stubs.RepositoryStub;
import sharedRegions.DepartureAirport.IHostessDP;
import sharedRegions.Repository.IRepository;
import threads.Hostess;

public class HostessMain {

    public static void main(String args[]) {
        IHostessDP departureAirport = new DepartureAirportStub(ServerInformation.DEPARTUREAIRPORTHOSTNAME, ServerInformation.DEPARTUREAIRPORTSERVERPORT);

        IRepository repository = new RepositoryStub(ServerInformation.REPOSITORYHOSTNAME, ServerInformation.REPOSITORYPORT);

        Hostess pilot = new Hostess(ServerInformation.NPASSENGERS, departureAirport, repository);
        pilot.start();
        try {
            pilot.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
