package stubs;

import sharedRegions.Plane.IPassengerP;
import sharedRegions.Plane.IPilotP;

public class PlaneStub implements IPassengerP, IPilotP {
    private String serverHostname;
    private int serverPort;

    public PlaneStub(String hostname, int port) {
        serverHostname = hostname;
        serverPort = port;
    }

    /*
     _ __   __ _ ___ ___  ___ _ __   __ _  ___ _ __
    | '_ \ / _` / __/ __|/ _ \ '_ \ / _` |/ _ \ '__|
    | |_) | (_| \__ \__ \  __/ | | | (_| |  __/ |
    | .__/ \__,_|___/___/\___|_| |_|\__, |\___|_|
    | |                              __/ |
    |_|                             |___/
    */

    public int boardPlane() {
        return 0;
    }
    public int waitForPlaneToLand() {
        return 0;
    }
    public int leavePlane() {
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

    public int announceArrival() {
        return 0;
    }
    public int waitingForDeboarding() {
        return 0;
    }

}
