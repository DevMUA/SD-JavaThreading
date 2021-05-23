package proxies;

import common.Message;
import common.MethodType;
import common.ServiceProvider;
import sharedRegions.Repository.Repository;
import state.SHostess;
import state.SPassenger;
import state.SPilot;

import java.awt.*;
import java.lang.reflect.Method;

public class RepositoryProxy implements SharedRegionProxy {

    private Repository repository;

    private boolean isRunning;

    public RepositoryProxy(Repository repository) {
        this.repository = repository;
        this.isRunning = true;
    }

    public Message processAndReply(Message msg) {
        Message response = new Message(false);
        ServiceProvider sp = (ServiceProvider) Thread.currentThread();

        MethodType type = msg.getMethodType();
        int passengerID = msg.getPassengerID();

        int inq = msg.getInq();
        int inf = msg.getInf();
        int ptal = msg.getPtal();

        SPilot pilotState = msg.getPilotState();
        ;
        SHostess hostessState = msg.getHostessState();
        SPassenger passengerState = msg.getPassengerState();

        if (pilotState != null) {
            repository.update(pilotState);
            response.setOperationDone(true);
        }

        else if (hostessState != null) {
            if (passengerID != -1)
                repository.update(passengerID, hostessState);
            else
                repository.update(hostessState);
            response.setOperationDone(true);
        }

        else if (passengerState != null) {
            repository.update(passengerID, passengerState);
            response.setOperationDone(true);
        }

        else if (inq != -1) {
            repository.updateInq(inq);
            response.setOperationDone(true);
        }
        else if (inf != -1) {
            repository.updateInf(inf);
            response.setOperationDone(true);
        }
        else if (ptal != -1) {
            repository.updatePtal(ptal);
            response.setOperationDone(true);
        }
        else if (type == MethodType.SUMUP) {
            repository.writeSumUp();
            response.setOperationDone(true);
            synchronized (this){
                isRunning = false;
            }
        }
        return response;
    }

    public synchronized boolean isRunning(){
        return isRunning;
    }
}
