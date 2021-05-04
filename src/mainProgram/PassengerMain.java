package mainProgram;

import common.ServerInformation;
import stubs.ArrivalAirportStub;
import stubs.DepartureAirportStub;
import stubs.PlaneStub;
import stubs.RepositoryStub;
import sharedRegions.DepartureAirport.IPassengerDP;
import sharedRegions.Plane.IPassengerP;
import sharedRegions.ArrivalAirport.IPassengerAR;
import sharedRegions.Repository.IRepository;
import threads.Passenger;

public class PassengerMain {

    public static void main(String args[]) {

        IPassengerDP departureAirport = new DepartureAirportStub(ServerInformation.DEPARTUREAIRPORTHOSTNAME, ServerInformation.DEPARTUREAIRPORTSERVERPORT);
        IPassengerP plane = new PlaneStub(ServerInformation.PLANEHOSTNAME, ServerInformation.PLANESERVERPORT);
        IPassengerAR arrivalAirport = new ArrivalAirportStub(ServerInformation.ARRIVALAIRPORTHOSTNAME, ServerInformation.ARRIVALAIRPORTSERVERPORT);

        IRepository repository = new RepositoryStub(ServerInformation.REPOSITORYHOSTNAME, ServerInformation.REPOSITORYPORT);

        Passenger passenger = new Passenger(Integer.parseInt(args[0]), departureAirport, plane, arrivalAirport, repository);
        passenger.start();
        try {
            passenger.join();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
