package stubs;

import common.ClientCom;
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
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }
    public int waitingForPassenger() {
        Hostess p = (Hostess) Thread.currentThread();

        Message message = new Message(MethodType.WAITINGFORPASSENGER);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }
    public int askForDocuments() {
        Hostess p = (Hostess) Thread.currentThread();

        Message message = new Message(MethodType.ASKFORDOCUMENTS);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }
    public int waitingToCheckPassenger() {
        Hostess p = (Hostess) Thread.currentThread();

        Message message = new Message(MethodType.WAITINGTOCHECKPASSENGER);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }

    // preciso de dar fix a este boolean later;
    public boolean informReadyToFly() {
        Hostess p = (Hostess) Thread.currentThread();

        Message message = new Message(MethodType.INFORMREADYTOFLY);
        message.sethostessAttendendedAllPassengers(p.allPassengersAttended());
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return response.getResponseBoolValue();
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
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();

        cc.writeObject(message);

        Message response = (Message) cc.readObject();

        return 0;
    }
    public int waitInQueue() {
        Passenger p = (Passenger) Thread.currentThread();

        Message message = new Message(p.getPassengerID(), MethodType.WAITINQUEUE);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }
    public int showDocuments() {
        Passenger p = (Passenger) Thread.currentThread();

        Message message = new Message(p.getPassengerID(), MethodType.SHOWDOCUMENTS);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }
    public int waitingToBeCheckedIn() {
        Passenger p = (Passenger) Thread.currentThread();

        Message message = new Message(p.getPassengerID(), MethodType.WAITINGTOBECHECKEDIN);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
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
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }
    public int waitingForBoarding() {
        Pilot p = (Pilot) Thread.currentThread();

        Message message = new Message(MethodType.WAITINGFORBOARDING);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }
    public boolean isInformPilotToCeaseActivity() {
        Pilot p = (Pilot) Thread.currentThread();

        Message message = new Message(MethodType.ISINFORMPILOTTOCEASEACTIVITY);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return response.getResponseBoolValue();
    }
}

