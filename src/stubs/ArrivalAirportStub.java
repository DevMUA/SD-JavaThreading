package stubs;

import common.ClientCom;
import common.Message;
import common.MethodType;
import sharedRegions.ArrivalAirport.IPassengerAR;
import threads.Passenger;

public class ArrivalAirportStub implements IPassengerAR {

    private final String serverHostname;
    private final int serverPort;

    public ArrivalAirportStub(String hostname, int port) {
        serverHostname = hostname;
        serverPort = port;
    }

    public int leaveAirport() {
        Passenger p = (Passenger) Thread.currentThread();

        Message message = new Message(p.getPassengerID(), MethodType.LEAVEAIRPORT);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }
}
