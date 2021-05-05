package common;

public class ServerInformation {


    //Departure Airport settings
    public static final int MINPLANEPASSENGERS = 5;
    public static final int MAXPLANEPASSENGERS = 10;

    public static final String localHost = "127.0.0.1";
    public static final String DEPARTUREAIRPORTHOSTNAME = localHost;
    public static final int DEPARTUREAIRPORTSERVERPORT = 6001;
    public static final String ARRIVALAIRPORTHOSTNAME = localHost;
    public static final int ARRIVALAIRPORTSERVERPORT = 6002;
    public static final String PLANEHOSTNAME = localHost;
    public static final int PLANESERVERPORT = 6003;
    public static final String REPOSITORYHOSTNAME = localHost;
    public static final int REPOSITORYPORT = 6004;

    public static final int NPASSENGERS = 1;

    public static final int PLANETRAVELTIME = 5000;//GeneralTool.getRandomNumber(1,2) * 1000;

    public  static final int  AIRPORTTRAVELTIME = GeneralTool.getRandomNumber(5,25) * 1000;

}
