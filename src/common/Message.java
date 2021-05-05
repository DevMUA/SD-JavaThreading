package common;

import common.ClientCom;

import java.io.Serializable;

public class Message implements Serializable {

    //PASSENGER ID
    private int id;

    private MethodType methodType;

    private boolean operationDone;
    private boolean responseBoolValue;

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

    public void setResponseBoolValue(boolean value){
        this.responseBoolValue = value;
    }

    public void send(String hostname, int port) {
        ClientCom cc = new ClientCom(hostname, port);
        cc.open();
        cc.writeObject(this);
    }
}
