package stubs;

import common.ClientCom;
import common.Message;
import common.MethodType;
import sharedRegions.ArrivalAirport.IPassengerAR;

public class ArrivalAirportStub implements IPassengerAR {

    private String serverHostname;
    private int serverPort;

    public ArrivalAirportStub(String hostname, int port) {
        serverHostname = hostname;
        serverPort = port;
    }
    public int leaveAirport() {
        Message message = new Message(MethodType.LEAVEAIRPORT);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }
}
