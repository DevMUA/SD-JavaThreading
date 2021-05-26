package mainProgram;

import common.ServerCom;
import common.ServerInformation;
import common.ServiceProvider;
import proxies.RepositoryProxy;
import sharedRegions.Repository.Repository;

import java.net.SocketTimeoutException;

/**
 * The type Repository main.
 */
public class RepositoryMain {
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

        Repository repository = new Repository(ServerInformation.NPASSENGERS);
        RepositoryProxy repositoryProxy = new RepositoryProxy(repository);

        serverCom = new ServerCom(ServerInformation.REPOSITORYPORT);
        serverCom.start();

        while(repositoryProxy.isRunning()) {
            try {
                serverConn = serverCom.accept();

                serviceProvider = new ServiceProvider(repositoryProxy, serverConn);
                serviceProvider.start();
            }
            catch (SocketTimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
