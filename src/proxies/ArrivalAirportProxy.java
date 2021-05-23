package proxies;

import common.Message;
import common.ServerInformation;
import common.ServiceProvider;
import sharedRegions.ArrivalAirport.ArrivalAirport;

public class ArrivalAirportProxy implements SharedRegionProxy {

    private final ArrivalAirport arrivalAirport;

    private int passengersCount;

    private boolean isRunning;

    public ArrivalAirportProxy(ArrivalAirport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
        this.passengersCount = 0;
        isRunning = true;
    }

    public Message processAndReply(Message msg) {
        Message response = new Message(false);
        ServiceProvider sp = (ServiceProvider) Thread.currentThread();

        switch(msg.getMethodType()) {
            case LEAVEAIRPORT:
                sp.setPassengerID(msg.getPassengerID());
                arrivalAirport.leaveAirport();
                synchronized (this) {
                    passengersCount++;
                    if(passengersCount == ServerInformation.NPASSENGERS){
                        isRunning = false;
                    }
                }
                response.setOperationDone(true);
        }
        return response;
    }

    public synchronized boolean isRunning(){
        return isRunning;
    }
}
