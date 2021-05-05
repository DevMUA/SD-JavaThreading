package mainProgram;

import common.ServerCom;
import common.ServerInformation;
import common.ServiceProvider;
import proxies.ArrivalAirportProxy;
import sharedRegions.ArrivalAirport.ArrivalAirport;
import sharedRegions.Repository.Repository;

import java.net.SocketTimeoutException;

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

        serverCom = new ServerCom(ServerInformation.ARRIVALAIRPORTSERVERPORT);
        serverCom.start();

        while(arrivalAirportProxy.isRunning()){
            try {
                serverConn = serverCom.accept();

                serviceProvider = new ServiceProvider(arrivalAirportProxy, serverConn);
                serviceProvider.start();
            }
             catch (SocketTimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
