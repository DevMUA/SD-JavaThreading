package common;

public class Message {

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
}
