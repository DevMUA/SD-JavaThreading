package mainProgram;

import common.ServerCom;
import common.ServerInformation;
import common.ServiceProvider;
import proxies.DepartureAirportProxy;
import sharedRegions.DepartureAirport.DepartureAirport;
import sharedRegions.Repository.IRepository;
import sharedRegions.Repository.Repository;
import stubs.RepositoryStub;

import java.net.SocketTimeoutException;

/**
 * The type Departure airport main.
 */
public class DepartureAirportMain {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        //Main server connection channel
        ServerCom serverCom;

        ServiceProvider serviceProvider;

        //Connection to be attended by a thread
        ServerCom serverConn;

        IRepository repository = new RepositoryStub(ServerInformation.REPOSITORYHOSTNAME, ServerInformation.REPOSITORYPORT);

        DepartureAirport departureAirport = new DepartureAirport(ServerInformation.MINPLANEPASSENGERS,ServerInformation.MAXPLANEPASSENGERS,repository);
        DepartureAirportProxy departureAirportProxy = new DepartureAirportProxy(departureAirport);

        serverCom = new ServerCom(ServerInformation.DEPARTUREAIRPORTSERVERPORT);
        serverCom.start();

        while(departureAirportProxy.isRunning()){
            try {
                serverConn = serverCom.accept();

                serviceProvider = new ServiceProvider(departureAirportProxy, serverConn);
                serviceProvider.start();
            }
            catch (SocketTimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
