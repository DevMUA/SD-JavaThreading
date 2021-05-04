package stubs;


import sharedRegions.Repository.IRepository;
import state.SHostess;
import state.SPassenger;
import state.SPilot;

public class RepositoryStub implements IRepository {

    private String serverHostname;
    private int serverPort;

    public RepositoryStub(String hostname, int port) {
        serverHostname = hostname;
        serverPort = port;
    }

    public void update(SPilot state){}
    public void update(SHostess state){}
    public void update(int passenger_no, SHostess state){}
    public void update(int passenger_no, SPassenger state){}

    public void updateInq(int inq){}
    public void updateInf(int inf){}
    public void updatePtal(int ptal){}

    public void writeSumUp(){}

}
