package mainProgram;

import common.ServerInformation;
import stubs.ArrivalAirportStub;
import stubs.DepartureAirportStub;
import stubs.PlaneStub;
import sharedRegions.DepartureAirport.IPassengerDP;
import sharedRegions.Plane.IPassengerP;
import sharedRegions.ArrivalAirport.IPassengerAR;
import threads.Passenger;
import common.Parameters;

public class PassengerMain {

    IPassengerDP departureAirport = new DepartureAirportStub(ServerInformation.DEPARTUREAIRPORTHOSTNAME, ServerInformation.DEPARTUREAIRPORTSERVERPORT);
    IPassengerP plane = new PlaneStub(ServerInformation.PLANEHOSTNAME, ServerInformation.PLANESERVERPORT);
    IPassengerAR arrivalAirport = new ArrivalAirportStub(ServerInformation.ARRIVALAIRPORTHOSTNAME, ServerInformation.ARRIVALAIRPORTSERVERPORT);

    //RepositoryStub repository = new RepositoryStub();

    Passenger passenger = new Passenger(Integer.parseInt(args[0]), departureAirport, plane, arrivalAirport);

    passenger.start();
    try {
        passenger.join();
    } catch(InterruptedException interruptedException) {
        interruptedException.printStackTrace();
    }
}
