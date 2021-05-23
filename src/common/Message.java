package common;

import state.SHostess;
import state.SPassenger;
import state.SPilot;

import java.io.Serializable;

public class Message implements Serializable {

    //PASSENGER ID
    private int passengerID = -1;

    private boolean operationDone = false;
    private boolean responseBoolValue = false;
    private boolean hostessAttendendedAllPassengers = false;

    // Repository Variables
    private int inq = -1;
    private int inf = -1;
    private int ptal = -1;

    private SPilot pilotState = null;
    private SHostess hostessState = null;
    private SPassenger passengerState = null;

    private MethodType methodType = MethodType.UPDATEREPO;

    public Message() {}
    public Message(MethodType methodType) {
        this.methodType = methodType;
    }
    public Message(int passengerID, MethodType methodType) {
        this.passengerID = passengerID;
        this.methodType = methodType;
    }
    public Message(Boolean operationDone){
        this.operationDone = operationDone;
    }


    public Message(SPilot state)   { this.pilotState = state; }
    public Message(SHostess state) { this.hostessState = state; }
    public Message(int passengerID, SHostess state) {
        this.passengerID = passengerID;
        this.hostessState = state;
    }
    public Message(int passengerID, SPassenger state) {
        this.passengerID = passengerID;
        this.passengerState = state;
    }

    public MethodType getMethodType() { return methodType; }
    public int getPassengerID() { return passengerID; }
    public boolean isOperationDone() { return operationDone; }
    public boolean getResponseBoolValue() { return responseBoolValue; }
    public boolean hasHostessAttendendedAllPassengers() { return hostessAttendendedAllPassengers; }

    public int getInq() { return inq; }
    public int getInf() { return inf; }
    public int getPtal() { return ptal; }

    public SPilot getPilotState() { return pilotState; }
    public SHostess getHostessState() { return hostessState; }
    public SPassenger getPassengerState() { return passengerState; }


    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }
    public void setPassengerID(int passengerID) { this.passengerID = passengerID; }
    public void setOperationDone(boolean operationDone) { this.operationDone = operationDone; }
    public void setResponseBoolValue(boolean responseBoolValue) { this.responseBoolValue = responseBoolValue; }
    public void setHostessAttendendedAllPassengers(boolean hostessAttendendedAllPassengers) { this.hostessAttendendedAllPassengers = hostessAttendendedAllPassengers; }

    public void setInq(int inq) { this.inq = inq; }
    public void setInf(int inf) { this.inf = inf; }
    public void setPtal(int ptal) { this.ptal = ptal; }

    public void setPilotState(SPilot pilotState) { this.pilotState = pilotState; }
    public void setHostessState(SHostess hostessState) { this.hostessState = hostessState; }
    public void setPassengerState(SPassenger passengerState) { this.passengerState = passengerState; }
}
