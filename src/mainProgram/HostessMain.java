package mainProgram;

import common.ServerInformation;
import stubs.DepartureAirportStub;
import sharedRegions.DepartureAirport.IHostessDP;
import threads.Hostess;

public class HostessMain {

    IHostessDP departureAirport = new DepartureAirportStub(ServerInformation.DEPARTUREAIRPORTHOSTNAME, ServerInformation.DEPARTUREAIRPORTSERVERPORT);

    //RepositoryStub repository = new RepositoryStub();

    Hostess pilot = new Hostess(ServerInformation.NPASSENGERS,departureAirport);

    pilot.start();
    try{
        pilot.join();
    } catch(InterruptedException e) {
        e.printStackTrace();
    }
