package mainProgram;

import stubs.ArrivalAirportStub;
import stubs.DepartureAirportStub;
import stubs.PlaneStub;
import stubs.RepositoryStub;
import sharedRegions.DepartureAirport.IPassengerDP;
import sharedRegions.Plane.IPassengerP;
import sharedRegions.ArrivalAirport.IPassengerAR;
import threads.Passenger;
import common.Parameters;

public class PassengerMain {

    IPassengerDP departureAirport = new DepartureAirportStub(Parameters.DEPARTUREAIRPORTHOSTNAME, Parameters.DEPARTUREAIRPORTPORT);
    IPassengerP plane = new PlaneStub(Parameters.PLANEHOSTNAME, Parameters.PLANEPORT);
    IPassengerAR arrivalAirport = new ArrivalAirportStub(Parameters.ARRIVALAIRPORTHOSTNAME, Parameters.ARRIVALAIRPORTPORT);

    //RepositoryStub repository = new RepositoryStub();

    Passenger passenger = new Passenger(Integer.parseInt(args[0]), departureAirport, plane, arrivalAirport);

    passenger.start();
    try {
        passenger.join();
    } catch(InterruptedException interruptedException) {
        interruptedException.printStackTrace();
    }
}
