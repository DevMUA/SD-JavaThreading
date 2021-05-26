package common;

import proxies.SharedRegionProxy;

/**
 * The type Service provider.
 */
public class ServiceProvider extends Thread implements HostessInterface, PilotInterface, PassengerInterface {

    private final SharedRegionProxy sharedRegion;

    private final ServerCom serverCom;

    //Passenger ID
    private int id;

    private boolean allPassengersAttended;

    /**
     * Instantiates a new Service provider.
     *
     * @param sharedRegion the shared region
     * @param serverCom    the server com
     */
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

    /**
     * Set passenger id.
     *
     * @param id the id
     */
    public void setPassengerID(int id){
        this.id = id;
    }

    /**
     * Sets all passengers attended.
     *
     * @param value the value
     */
    public void setAllPassengersAttended(boolean value) {
        allPassengersAttended = value;
    }

    public boolean allPassengersAttended(){
        if(allPassengersAttended)
            return true;
        else
            return false;
    }
}
