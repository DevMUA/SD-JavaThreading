package proxies;

import common.Message;
import common.ServiceProvider;
import sharedRegions.ArrivalAirport.ArrivalAirport;

public class ArrivalAirportProxy implements SharedRegionProxy{

    private final ArrivalAirport arrivalAirport;

    private boolean isRunning;

    public ArrivalAirportProxy(ArrivalAirport arrivalAirport){
        this.arrivalAirport = arrivalAirport;
        isRunning = true;
    }

    public Message processAndReply(Message msg){
        Message response = new Message(false);
        ServiceProvider sp = (ServiceProvider) Thread.currentThread();

        switch(msg.getMethodType()){
            case LEAVEAIRPORT:
                sp.setPassengerID(msg.getID());
                arrivalAirport.leaveAirport();
                response.setOperationDone(true);
        }
        return response;
    }

    public synchronized boolean isRunning(){
        return isRunning;
    }
}
