package common;

import state.SHostess;
import state.SPassenger;
import state.SPilot;

import java.io.Serializable;

/**
 * The type Message.
 */
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

    /**
     * Instantiates a new Message.
     */
    public Message() {}

    /**
     * Instantiates a new Message.
     *
     * @param methodType the method type
     */
    public Message(MethodType methodType) {
        this.methodType = methodType;
    }

    /**
     * Instantiates a new Message.
     *
     * @param passengerID the passenger id
     * @param methodType  the method type
     */
    public Message(int passengerID, MethodType methodType) {
        this.passengerID = passengerID;
        this.methodType = methodType;
    }

    /**
     * Instantiates a new Message.
     *
     * @param operationDone the operation done
     */
    public Message(Boolean operationDone){
        this.operationDone = operationDone;
    }


    /**
     * Instantiates a new Message.
     *
     * @param state the state
     */
    public Message(SPilot state)   { this.pilotState = state; }

    /**
     * Instantiates a new Message.
     *
     * @param state the state
     */
    public Message(SHostess state) { this.hostessState = state; }

    /**
     * Instantiates a new Message.
     *
     * @param passengerID the passenger id
     * @param state       the state
     */
    public Message(int passengerID, SHostess state) {
        this.passengerID = passengerID;
        this.hostessState = state;
    }

    /**
     * Instantiates a new Message.
     *
     * @param passengerID the passenger id
     * @param state       the state
     */
    public Message(int passengerID, SPassenger state) {
        this.passengerID = passengerID;
        this.passengerState = state;
    }

    /**
     * Gets method type.
     *
     * @return the method type
     */
    public MethodType getMethodType() { return methodType; }

    /**
     * Gets passenger id.
     *
     * @return the passenger id
     */
    public int getPassengerID() { return passengerID; }

    /**
     * Is operation done boolean.
     *
     * @return the boolean
     */
    public boolean isOperationDone() { return operationDone; }

    /**
     * Gets response bool value.
     *
     * @return the response bool value
     */
    public boolean getResponseBoolValue() { return responseBoolValue; }

    /**
     * Has hostess attendended all passengers boolean.
     *
     * @return the boolean
     */
    public boolean hasHostessAttendendedAllPassengers() { return hostessAttendendedAllPassengers; }

    /**
     * Gets inq.
     *
     * @return the inq
     */
    public int getInq() { return inq; }

    /**
     * Gets inf.
     *
     * @return the inf
     */
    public int getInf() { return inf; }

    /**
     * Gets ptal.
     *
     * @return the ptal
     */
    public int getPtal() { return ptal; }

    /**
     * Gets pilot state.
     *
     * @return the pilot state
     */
    public SPilot getPilotState() { return pilotState; }

    /**
     * Gets hostess state.
     *
     * @return the hostess state
     */
    public SHostess getHostessState() { return hostessState; }

    /**
     * Gets passenger state.
     *
     * @return the passenger state
     */
    public SPassenger getPassengerState() { return passengerState; }


    /**
     * Sets method type.
     *
     * @param methodType the method type
     */
    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }

    /**
     * Sets passenger id.
     *
     * @param passengerID the passenger id
     */
    public void setPassengerID(int passengerID) { this.passengerID = passengerID; }

    /**
     * Sets operation done.
     *
     * @param operationDone the operation done
     */
    public void setOperationDone(boolean operationDone) { this.operationDone = operationDone; }

    /**
     * Sets response bool value.
     *
     * @param responseBoolValue the response bool value
     */
    public void setResponseBoolValue(boolean responseBoolValue) { this.responseBoolValue = responseBoolValue; }

    /**
     * Sets hostess attendended all passengers.
     *
     * @param hostessAttendendedAllPassengers the hostess attendended all passengers
     */
    public void setHostessAttendendedAllPassengers(boolean hostessAttendendedAllPassengers) { this.hostessAttendendedAllPassengers = hostessAttendendedAllPassengers; }

    /**
     * Sets inq.
     *
     * @param inq the inq
     */
    public void setInq(int inq) { this.inq = inq; }

    /**
     * Sets inf.
     *
     * @param inf the inf
     */
    public void setInf(int inf) { this.inf = inf; }

    /**
     * Sets ptal.
     *
     * @param ptal the ptal
     */
    public void setPtal(int ptal) { this.ptal = ptal; }

    /**
     * Sets pilot state.
     *
     * @param pilotState the pilot state
     */
    public void setPilotState(SPilot pilotState) { this.pilotState = pilotState; }

    /**
     * Sets hostess state.
     *
     * @param hostessState the hostess state
     */
    public void setHostessState(SHostess hostessState) { this.hostessState = hostessState; }

    /**
     * Sets passenger state.
     *
     * @param passengerState the passenger state
     */
    public void setPassengerState(SPassenger passengerState) { this.passengerState = passengerState; }
}
