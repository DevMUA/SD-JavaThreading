package stubs;

import sharedRegions.DepartureAirport.IHostessDP;
import sharedRegions.DepartureAirport.IPassengerDP;
import sharedRegions.DepartureAirport.IPilotDP;

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
        return 0;
    }
    public int waitingForPassenger() {
        return 0;
    }
    public int askForDocuments() {
        return 0;
    }
    public int waitingToCheckPassenger() {
        return 0;
    }

    // preciso de dar fix a este boolean later;
    public boolean informReadyToFly() {
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

        return 0;
    }
    public int waitInQueue() {

        return 0;
    }
    public int showDocuments() {

        return 0;
    }
    public int waitingToBeCheckedIn() {

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
        return 0;
    }
    public int waitingForBoarding() {
        return 0;
    }
}

