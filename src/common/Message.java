package common;

import common.ClientCom;

public class Message {

    //PASSENGER ID
    private int id;

    private MethodType methodType;

    private boolean operationDone;

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

    public void setOperationDone(Boolean value){
        this.operationDone = value;
    }

    public void send(String hostname, int port) {
        ClientCom cc = new ClientCom(hostname , port);
        cc.open();
        cc.writeObject(this);
    }
}
