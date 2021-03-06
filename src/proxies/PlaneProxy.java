package proxies;

import common.Message;
import common.ServiceProvider;
import sharedRegions.Plane.Plane;

/**
 * The type Plane proxy.
 */
public class PlaneProxy implements SharedRegionProxy {

    private Plane plane;

    private boolean isRunning;

    /**
     * Instantiates a new Plane proxy.
     *
     * @param plane the plane
     */
    public PlaneProxy(Plane plane){
        this.plane = plane;
        this.isRunning = true;
    }

    public Message processAndReply(Message msg) {
        Message response = new Message(false);
        ServiceProvider sp = (ServiceProvider) Thread.currentThread();

        switch(msg.getMethodType()){
            case BOARDPLANE:
                sp.setPassengerID(msg.getPassengerID());
                plane.boardPlane();
                response.setOperationDone(true);
                break;

            case WAITFORPLANETOLAND:
                sp.setPassengerID(msg.getPassengerID());
                plane.waitForPlaneToLand();
                response.setOperationDone(true);
                break;

            case LEAVEPLANE:
                sp.setPassengerID(msg.getPassengerID());
                plane.leavePlane();
                response.setOperationDone(true);
                break;

            case ANNOUNCEARRIVAL:
                plane.announceArrival();
                response.setOperationDone(true);
                break;

            case WAITINGFORDEBOARDING:
                plane.waitingForDeboarding();
                response.setOperationDone(true);
                break;

            case INFORMPLANEISNOLONGERNEEDED:
                response.setOperationDone(true);
                synchronized (this){
                    isRunning = false;
                }
        }
        return response;
    }

    public synchronized boolean isRunning(){
        return isRunning;
    }

}
