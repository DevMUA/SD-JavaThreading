package mainProgram;

import Common.ServerCom;
import Common.ServerInformation;
import Common.ServiceProvider;
import proxies.ArrivalAirportProxy;
import proxies.DepartureAirportProxy;
import sharedRegions.ArrivalAirport.ArrivalAirport;
import sharedRegions.Repository.Repository;

public class ArrivalAirportMain {
    public static void main(String[] args) {

        //Main server connection channel
        ServerCom serverCom;

        ServiceProvider serviceProvider;

        //Connection to be attended by a thread
        ServerCom serverConn;

        Repository temporaryFix = new Repository(20);
        ArrivalAirport arrivalAirport = new ArrivalAirport(temporaryFix);
        ArrivalAirportProxy arrivalAirportProxy = new ArrivalAirportProxy(arrivalAirport);

        serverCom = new ServerCom(ServerInformation.departureAirportServerPort);
        serverCom.start();

        while(arrivalAirportProxy.isRunning()){
            serverConn = serverCom.accept();
            serviceProvider = new ServiceProvider(arrivalAirportProxy,serverConn);
            serviceProvider.start();
        }
    }
}
