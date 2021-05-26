package common;

/**
 * The type Server information.
 */
public class ServerInformation {


    /**
     * The constant MINPLANEPASSENGERS.
     */
//Departure Airport settings
    public static final int MINPLANEPASSENGERS = 5;
    /**
     * The constant MAXPLANEPASSENGERS.
     */
    public static final int MAXPLANEPASSENGERS = 10;

    /**
     * The constant localHost.
     */
    public static final String localHost = "127.0.0.1";
    /**
     * The constant DEPARTUREAIRPORTHOSTNAME.
     */
    public static final String DEPARTUREAIRPORTHOSTNAME = localHost;
    /**
     * The constant DEPARTUREAIRPORTSERVERPORT.
     */
    public static final int DEPARTUREAIRPORTSERVERPORT = 6001;
    /**
     * The constant ARRIVALAIRPORTHOSTNAME.
     */
    public static final String ARRIVALAIRPORTHOSTNAME = localHost;
    /**
     * The constant ARRIVALAIRPORTSERVERPORT.
     */
    public static final int ARRIVALAIRPORTSERVERPORT = 6002;
    /**
     * The constant PLANEHOSTNAME.
     */
    public static final String PLANEHOSTNAME = localHost;
    /**
     * The constant PLANESERVERPORT.
     */
    public static final int PLANESERVERPORT = 6003;
    /**
     * The constant REPOSITORYHOSTNAME.
     */
    public static final String REPOSITORYHOSTNAME = localHost;
    /**
     * The constant REPOSITORYPORT.
     */
    public static final int REPOSITORYPORT = 6004;

    /**
     * The constant NPASSENGERS.
     */
    public static final int NPASSENGERS = 21;

    /**
     * The constant PLANETRAVELTIME.
     */
    public static final int PLANETRAVELTIME = 5000;//GeneralTool.getRandomNumber(1,2) * 1000;

    /**
     * The constant AIRPORTTRAVELTIME.
     */
    public  static final int  AIRPORTTRAVELTIME = GeneralTool.getRandomNumber(5,25) * 1000;

}
