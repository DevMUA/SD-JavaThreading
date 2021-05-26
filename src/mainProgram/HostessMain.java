package mainProgram;

import common.ServerInformation;
import stubs.DepartureAirportStub;
import stubs.RepositoryStub;
import sharedRegions.DepartureAirport.IHostessDP;
import sharedRegions.Repository.IRepository;
import threads.Hostess;

/**
 * The type Hostess main.
 */
public class HostessMain {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        IHostessDP departureAirport = new DepartureAirportStub(ServerInformation.DEPARTUREAIRPORTHOSTNAME, ServerInformation.DEPARTUREAIRPORTSERVERPORT);

        IRepository repository = new RepositoryStub(ServerInformation.REPOSITORYHOSTNAME, ServerInformation.REPOSITORYPORT);

        Hostess hostess = new Hostess(ServerInformation.NPASSENGERS, departureAirport, repository);
        hostess.start();

        try {
            hostess.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
