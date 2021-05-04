package stubs;

import common.Message;
import common.MethodType;
import sharedRegions.DepartureAirport.IHostessDP;
import sharedRegions.DepartureAirport.IPassengerDP;
import sharedRegions.DepartureAirport.IPilotDP;
import threads.Hostess;
import threads.Passenger;
import threads.Pilot;

public class DepartureAirportStub implements IHostessDP, IPassengerDP, IPilotDP {

    private String serverHostname;
    private int serverPort;

    public DepartureAirportStub(String hostname, int port) {
        serverHostname = hostname;
        serverPort = port;
    }

    /*
         _               _
        | |             | |
        | |__   ___  ___| |_ ___  ___ ___
        | '_ \ / _ \/ __| __/ _ \/ __/ __|
        | | | | (_) \__ \ ||  __/\__ \__ \
        |_| |_|\___/|___/\__\___||___/___/
    */

    public int waitingForNextFlight() {
        Hostess p = (Hostess) Thread.currentThread();

        Message message = new Message(MethodType.WAITINGFORNEXTFLIGHT);
        message.send(serverHostname, serverPort);
        return 0;
    }
    public int waitingForPassenger() {
        Hostess p = (Hostess) Thread.currentThread();

        Message message = new Message(MethodType.WAITINGFORPASSENGER);
        message.send(serverHostname, serverPort);
        return 0;
    }
    public int askForDocuments() {
        Hostess p = (Hostess) Thread.currentThread();

        Message message = new Message(MethodType.ASKFORDOCUMENTS);
        message.send(serverHostname, serverPort);
        return 0;
    }
    public int waitingToCheckPassenger() {
        Hostess p = (Hostess) Thread.currentThread();

        Message message = new Message(MethodType.WAITINGTOCHECKPASSENGER);
        message.send(serverHostname, serverPort);
        return 0;
    }

    // preciso de dar fix a este boolean later;
    public boolean informReadyToFly() {
        //?????
        return true;
    }

    /*
     _ __   __ _ ___ ___  ___ _ __   __ _  ___ _ __
    | '_ \ / _` / __/ __|/ _ \ '_ \ / _` |/ _ \ '__|
    | |_) | (_| \__ \__ \  __/ | | | (_| |  __/ |
    | .__/ \__,_|___/___/\___|_| |_|\__, |\___|_|
    | |                              __/ |
    |_|                             |___/
     */

    public int travelToAirport() {
        Passenger p = (Passenger) Thread.currentThread();

        Message message = new Message(p.getPassengerID(), MethodType.TRAVELTOAIPORT);
        message.send(serverHostname, serverPort);
        return 0;
    }
    public int waitInQueue() {
        Passenger p = (Passenger) Thread.currentThread();

        Message message = new Message(p.getPassengerID(), MethodType.WAITINQUEUE);
        message.send(serverHostname, serverPort);
        return 0;
    }
    public int showDocuments() {
        Passenger p = (Passenger) Thread.currentThread();

        Message message = new Message(p.getPassengerID(), MethodType.SHOWDOCUMENTS);
        message.send(serverHostname, serverPort);
        return 0;
    }
    public int waitingToBeCheckedIn() {
        Passenger p = (Passenger) Thread.currentThread();

        Message message = new Message(p.getPassengerID(), MethodType.WAITINGTOBECHECKEDIN);
        message.send(serverHostname, serverPort);
        return 0;
    }

    /*
           _ _       _
          (_) |     | |
     _ __  _| | ___ | |_
    | '_ \| | |/ _ \| __|
    | |_) | | | (_) | |_
    | .__/|_|_|\___/ \__|
    | |
    |_|
     */

    public int informReadyBoarding() {
        Pilot p = (Pilot) Thread.currentThread();

        Message message = new Message(MethodType.INFORMREADYFORBOARDING);
        message.send(serverHostname, serverPort);
        return 0;
    }
    public int waitingForBoarding() {
        Pilot p = (Pilot) Thread.currentThread();

        Message message = new Message(MethodType.WAITINGFORBOARDING);
        message.send(serverHostname, serverPort);
        return 0;
    }
    public boolean isInformPilotToCeaseActivity() {
        /*Todo
        Pilot p = (Pilot) Thread.currentThread();

        Message message = new Message(MethodType.WAITINGFORBOARDING);
        message.send(serverHostname, serverPort);
        return 0;*/
        return true;
    }
}

