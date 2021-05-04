package common;

import common.GeneralTool;

public class Parameters {

    private static final int NPASSENGERS = 21;
    private static final int MINPLANEPASSENGERS = 5;
    private static final int MAXPLANEPASSENGERS = 10;

    public static final int PLANETRAVELTIME = GeneralTool.getRandomNumber(1,2) * 1000;

    public  static final int  AIRPORTTRAVELTIME = GeneralTool.getRandomNumber(5,25) * 1000;

    // nota: mudar os nomes de hostname e ports??
    public static final String DEPARTUREAIRPORTHOSTNAME = "l040101-ws01.ua.pt";
    public static final int DEPARTUREAIRPORTPORT = 22120;

    public static final String PLANEHOSTNAME = "l040101-ws02.ua.pt";
    public static final int PLANEPORT = 22120;

    public static final String ARRIVALAIRPORTHOSTNAME = "l040101-ws03.ua.pt";
    public static final int ARRIVALAIRPORTPORT = 22120;
}
