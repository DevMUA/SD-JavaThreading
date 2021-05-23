package threads;

import sharedRegions.DepartureAirport.IHostessDP;
import sharedRegions.Repository.IRepository;

public class Hostess extends Thread {

    private final int totalNumberOfPassengers;
    private int attendedPassengers;

    //References to shared regions
    private final IHostessDP departureAirport;

    // Information Repository
    private final IRepository repository;

    public Hostess(int totalNumberOfPassengers, IHostessDP departureAirport, IRepository repository) {
        this.totalNumberOfPassengers = totalNumberOfPassengers;
        this.departureAirport = departureAirport;
        this.repository = repository;
        this.attendedPassengers = 0;
    }

    @Override
    public void run() {

        while(!allPassengersAttended()) {
            boolean flightFull = false;

            System.out.println(("Hostess: Esperar voo"));
            departureAirport.waitingForNextFlight();

            while(!flightFull) {
                System.out.println(("Hostess: Esperar passageiros"));
                departureAirport.waitingForPassenger();
                System.out.println(("Hostess: Pedir Documentos"));
                departureAirport.askForDocuments();
                System.out.println(("Hostess: Esperar por checkar passageiro"));
                departureAirport.waitingToCheckPassenger();
                attendedPassengers++;
                System.out.println(("Hostess: Informar pronto para voar?"));
                flightFull = departureAirport.informReadyToFly();
            }
        }
    }

    public boolean allPassengersAttended(){
        return attendedPassengers == totalNumberOfPassengers;
    }
}
