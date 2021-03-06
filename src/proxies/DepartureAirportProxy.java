package proxies;

import common.Message;
import common.ServiceProvider;
import sharedRegions.DepartureAirport.DepartureAirport;

/**
 * The type Departure airport proxy.
 */
public class DepartureAirportProxy implements SharedRegionProxy {

    private DepartureAirport departureAirport;

    private boolean isRunning;

    /**
     * Instantiates a new Departure airport proxy.
     *
     * @param departureAirport the departure airport
     */
    public DepartureAirportProxy(DepartureAirport departureAirport) {
        this.departureAirport = departureAirport;
        this.isRunning = true;
    }

    public Message processAndReply(Message msg) {
        Message response = new Message(false);
        ServiceProvider sp = (ServiceProvider) Thread.currentThread();

        switch(msg.getMethodType()){
            case WAITINGFORNEXTFLIGHT:
                departureAirport.waitingForNextFlight();
                response.setOperationDone(true);
                break;

            case WAITINGFORPASSENGER:
                departureAirport.waitingForPassenger();
                response.setOperationDone(true);
                break;

            case ASKFORDOCUMENTS:
                departureAirport.askForDocuments();
                response.setOperationDone(true);
                break;

            case WAITINGTOCHECKPASSENGER:
                departureAirport.waitingToCheckPassenger();
                response.setOperationDone(true);
                break;

            case INFORMREADYTOFLY:
                sp.setAllPassengersAttended(msg.hasHostessAttendendedAllPassengers());
                response.setResponseBoolValue(departureAirport.informReadyToFly());
                response.setOperationDone(true);
                break;

            case TRAVELTOAIPORT:
                sp.setPassengerID(msg.getPassengerID());
                departureAirport.travelToAirport();
                response.setOperationDone(true);
                break;

            case WAITINQUEUE:
                sp.setPassengerID(msg.getPassengerID());
                departureAirport.waitInQueue();
                response.setOperationDone(true);
                break;

            case SHOWDOCUMENTS:
                sp.setPassengerID(msg.getPassengerID());
                departureAirport.showDocuments();
                response.setOperationDone(true);
                break;

            case WAITINGTOBECHECKEDIN:
                sp.setPassengerID(msg.getPassengerID());
                departureAirport.waitingToBeCheckedIn();
                response.setOperationDone(true);
                break;

            case INFORMREADYFORBOARDING:
                departureAirport.informReadyBoarding();
                response.setOperationDone(true);
                break;

            case WAITINGFORBOARDING:
                departureAirport.waitingForBoarding();
                response.setOperationDone(true);
                break;

            case ISINFORMPILOTTOCEASEACTIVITY:
                boolean value = departureAirport.isInformPilotToCeaseActivity();
                response.setResponseBoolValue(value);
                response.setOperationDone(true);
                synchronized (this){
                    if(value){
                        isRunning = false;
                    }
                }
                break;

        }
        return response;
    }

    public synchronized boolean isRunning(){
        return isRunning;
    }

}
