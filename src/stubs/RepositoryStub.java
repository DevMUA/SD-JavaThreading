package stubs;

import common.ClientCom;
import common.Message;
import common.MethodType;
import sharedRegions.Repository.IRepository;
import state.SHostess;
import state.SPassenger;
import state.SPilot;

public class RepositoryStub implements IRepository {

    private final String serverHostname;
    private final int serverPort;

    public RepositoryStub(String hostname, int port) {
        serverHostname = hostname;
        serverPort = port;
    }

    public int update(SPilot state) {
        Message message = new Message(state);

        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();

        cc.close();
        return (response.isOperationDone()) ? 0 : 1;
    }

    public int update(SHostess state) {
        Message message = new Message(state);

        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();

        cc.close();
        return (response.isOperationDone()) ? 0 : 1;
    }

    public int update(int passengerID, SHostess state) {
        Message message = new Message(passengerID, state);

        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();

        cc.close();
        return (response.isOperationDone()) ? 0 : 1;
    }

    public int update(int passengerID, SPassenger state) {
        Message message = new Message(passengerID, state);

        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();

        cc.close();
        return (response.isOperationDone()) ? 0 : 1;
    }

    public int updateInq(int inq) {
        Message message = new Message();
        message.setInq(inq);

        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();

        cc.close();
        return (response.isOperationDone()) ? 0 : 1;
    }

    public int updateInf(int inf) {
        Message message = new Message();
        message.setInf(inf);

        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();

        cc.close();
        return (response.isOperationDone()) ? 0 : 1;
    }

    public int updatePtal(int ptal) {
        Message message = new Message();
        message.setPtal(ptal);

        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();

        cc.close();
        return (response.isOperationDone()) ? 0 : 1;
    }

    public int writeSumUp() {
        Message message = new Message(MethodType.SUMUP);

        ClientCom cc = new ClientCom(serverHostname, serverPort);
        cc.open();
        cc.writeObject(message);

        Message response = (Message) cc.readObject();

        cc.close();
        return (response.isOperationDone()) ? 0 : 1;
    }
}
