package mainProgram;

import common.ServerCom;
import common.ServerInformation;
import common.ServiceProvider;
import proxies.PlaneProxy;
import sharedRegions.Plane.Plane;
import sharedRegions.Repository.IRepository;
import sharedRegions.Repository.Repository;
import stubs.RepositoryStub;

import java.net.SocketTimeoutException;

public class PlaneMain {
    public static void main(String[] args) {

        //Main server connection channel
        ServerCom serverCom;

        ServiceProvider serviceProvider;

        //Connection to be attended by a thread
        ServerCom serverConn;

        IRepository repository = new RepositoryStub(ServerInformation.REPOSITORYHOSTNAME, ServerInformation.REPOSITORYPORT);

        Plane plane = new Plane(repository);
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
                e.printStackTrace();
            }
        }
    }
}
