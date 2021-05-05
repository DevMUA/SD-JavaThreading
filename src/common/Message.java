package common;

import common.ClientCom;

import java.io.Serializable;

public class Message implements Serializable {

    //PASSENGER ID
    private int id;

    private MethodType methodType;

    private boolean operationDone;
    private boolean responseBoolValue;
    private boolean hostessAttendendedAllPassengers;

    public Message(int id, MethodType methodType){
        this.id = id;
        this.methodType = methodType;
    }

    public Message(MethodType methodType){
        this.id = -1;
        this.methodType = methodType;
    }

    public Message(Boolean operationDone){
        this.operationDone = operationDone;
    }

    public MethodType getMethodType(){
        return methodType;
    }

    public int getID(){
        return this.id;
    }

    public void setOperationDone(boolean value){
        this.operationDone = value;
    }

    public boolean getOperationDone(){
        return operationDone;
    }

    public void setResponseBoolValue(boolean value){
        this.responseBoolValue = value;
    }

    public boolean getResponseBoolValue(){
        return responseBoolValue;
    }

    public void sethostessAttendendedAllPassengers(boolean value){
        hostessAttendendedAllPassengers = value;
    }

    public boolean gethostessAttendendedAllPassengers(){
        return hostessAttendendedAllPassengers;
    }

}
