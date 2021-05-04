package proxies;

import common.Message;
import common.ServiceProvider;
import sharedRegions.DepartureAirport.DepartureAirport;

public class DepartureAirportProxy implements SharedRegionProxy{

    private DepartureAirport departureAirport;

    private boolean isRunning;

    public DepartureAirportProxy(DepartureAirport departureAirport){
        this.departureAirport = departureAirport;
        this.isRunning = true;
    }

    public Message processAndReply(Message msg){
        Message response = new Message(false);
        ServiceProvider sp = (ServiceProvider) Thread.currentThread();

        switch(msg.getMethodType()){
            case WAITINGFORNEXTFLIGHT:
                break;

            case WAITINGFORPASSENGER:
                break;

            case ASKFORDOCUMENTS:
                break;

            case WAITINGTOCHECKPASSENGER:
                break;

            case INFORMREADYTOFLY:
                break;

            case TRAVELTOAIPORT:
                break;

            case WAITINQUEUE:
                break;

            case SHOWDOCUMENTS:
                break;

            case WAITINGTOBECHECKEDIN:
                break;

            case INFORMREADYFORBOARDING:
                break;

            case WAITINGFORBOARDING:
                break;
        }
        return response;
    }

    public synchronized boolean isRunning(){
        return isRunning;
    }

}
