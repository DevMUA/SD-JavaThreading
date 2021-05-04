package stubs;

import sharedRegions.ArrivalAirport.IPassengerAR;

public class ArrivalAirportStub implements IPassengerAR {

    private String serverHostname;
    private int serverPort;

    public ArrivalAirportStub(String hostname, int port) {
        serverHostname = hostname;
        serverPort = port;
    }
    public int leaveAirport() {

        return 0;
    }
}
