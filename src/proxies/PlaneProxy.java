package proxies;

import Common.Message;
import Common.ServiceProvider;
import sharedRegions.Plane.Plane;

public class PlaneProxy implements SharedRegionProxy{

    private Plane plane;

    private boolean isRunning;

    public PlaneProxy(Plane plane){
        this.plane = plane;
        this.isRunning = true;
    }

    public Message processAndReply(Message msg){
        Message response = new Message(false);
        ServiceProvider sp = (ServiceProvider) Thread.currentThread();

        switch(msg.getMethodType()){
            case BOARDPLANE:
                break;

            case WAITFORPLANETOLAND:
                break;

            case LEAVEPLANE:
                break;

            case ANNOUNCEARRIVAL:
                break;

            case WAITINGFORDEBOARDING:
                break;
        }
        return response;
    }

    public synchronized boolean isRunning(){
        return isRunning;
    }

}
