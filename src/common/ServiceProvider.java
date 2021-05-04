package common;

import proxies.SharedRegionProxy;

public class ServiceProvider extends Thread implements HostessInterface,PilotInterface,PassengerInterface{

    private SharedRegionProxy sharedRegion;

    private ServerCom serverCom;

    //Passenger ID
    private int id;

    public ServiceProvider(SharedRegionProxy sharedRegion, ServerCom serverCom){
        this.sharedRegion = sharedRegion;
        this.serverCom = serverCom;
    }

    @Override
    public void run() {
        Message incomingMessage = (Message) serverCom.readObject();
        Message response = sharedRegion.processAndReply(incomingMessage);
        serverCom.writeObject(response);
        serverCom.close();
    }

    public int getPassengerID(){
        return id;
    }

    public void setPassengerID(int id){
        this.id = id;
    }
}
