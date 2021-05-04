package mainProgram;

import Common.ServerCom;
import Common.ServerInformation;
import Common.ServiceProvider;
import proxies.DepartureAirportProxy;
import proxies.SharedRegionProxy;
import sharedRegions.ArrivalAirport.ArrivalAirport;
import sharedRegions.DepartureAirport.DepartureAirport;
import sharedRegions.Repository.Repository;

public class DepartureAirportMain {

    public static void main(String[] args) {

        //Main server connection channel
        ServerCom serverCom;

        ServiceProvider serviceProvider;

        //Connection to be attended by a thread
        ServerCom serverConn;

        Repository temporaryFix = new Repository(20);
        DepartureAirport departureAirport = new DepartureAirport(ServerInformation.min,ServerInformation.max,temporaryFix);
        DepartureAirportProxy departureAirportProxy = new DepartureAirportProxy(departureAirport);

        serverCom = new ServerCom(ServerInformation.departureAirportServerPort);
        serverCom.start();

        while(departureAirportProxy.isRunning()){
                serverConn = serverCom.accept();
                serviceProvider = new ServiceProvider(departureAirportProxy,serverConn);
                serviceProvider.start();
        }
    }
}
