package mainProgram;

import common.ServerCom;
import common.ServerInformation;
import common.ServiceProvider;
import proxies.PlaneProxy;
import sharedRegions.Plane.Plane;
import sharedRegions.Repository.Repository;

import java.net.SocketTimeoutException;

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

        serverCom = new ServerCom(ServerInformation.PLANESERVERPORT);
        serverCom.start();

        while(planeProxy.isRunning()){
            try {
                serverConn = serverCom.accept();
                serviceProvider = new ServiceProvider(planeProxy, serverConn);
                serviceProvider.start();
            }
            catch(SocketTimeoutException e){

            }
        }
    }
}
