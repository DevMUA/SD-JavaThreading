package mainProgram;

import stubs.DepartureAirportStub;
import sharedRegions.DepartureAirport.IHostessDP;
import threads.Hostess;
import common.Parameters;

public class HostessMain {

    IHostessDP departureAirport = new DepartureAirportStub(Parameters.DEPARTUREAIRPORTHOSTNAME, Parameters.DEPARTUREAIRPORTPORT);

    //RepositoryStub repository = new RepositoryStub();

    Hostess pilot = new Hostess(Parameters.NPASSENGERS,departureAirport);

    pilot.start();
    try{
        pilot.join();
    } catch(InterruptedException e) {
        e.printStackTrace();
    }
