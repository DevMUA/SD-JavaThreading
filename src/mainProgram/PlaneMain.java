package mainProgram;

import Common.ServerCom;
import Common.ServerInformation;
import Common.ServiceProvider;
import proxies.DepartureAirportProxy;
import proxies.PlaneProxy;
import sharedRegions.DepartureAirport.DepartureAirport;
import sharedRegions.Plane.Plane;
import sharedRegions.Repository.Repository;

public class PlaneMain {
    public static void main(String[] args) {

        //Main server connection channel
        ServerCom serverCom;

        ServiceProvider serviceProvider;

        //Connection to be attended by a thread
        ServerCom serverConn;

        Repository temporaryFix = new Repository(20);
        Plane plane = new Plane(temporaryFix);
        PlaneProxy planeProxy = new PlaneProxy(plane);

        serverCom = new ServerCom(ServerInformation.departureAirportServerPort);
        serverCom.start();

        while(planeProxy.isRunning()){
            serverConn = serverCom.accept();
            serviceProvider = new ServiceProvider(planeProxy,serverConn);
            serviceProvider.start();
        }
    }
}
