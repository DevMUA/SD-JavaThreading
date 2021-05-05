package stubs;

import common.ClientCom;
import common.Message;
import common.MethodType;
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
        Message message = new Message(MethodType.BOARDPLANE);

        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }
    public int waitForPlaneToLand() {
        Message message = new Message(MethodType.WAITFORPLANETOLAND);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }
    public int leavePlane() {
        Message message = new Message(MethodType.LEAVEPLANE);
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

    public int announceArrival() {
        Message message = new Message(MethodType.ANNOUNCEARRIVAL);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }
    public int waitingForDeboarding() {
        Message message = new Message(MethodType.WAITINGFORDEBOARDING);
        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();
        return 0;
    }

}
