package sharedRegions.ArrivalAirport;

import common.PassengerInterface;
import common.ServiceProvider;
import sharedRegions.Repository.IRepository;

/**
 * The type Arrival airport.
 */
public class ArrivalAirport implements IPassengerAR {

    private int numberOfArrivedPassengers;

    // Information Repository
    private final IRepository repository;

    /**
     * Instantiates a new Arrival airport.
     *
     * @param repository the repository
     */
    public ArrivalAirport(IRepository repository) {
        this.repository = repository;
    }

    /*
     _ __   __ _ ___ ___  ___ _ __   __ _  ___ _ __
    | '_ \ / _` / __/ __|/ _ \ '_ \ / _` |/ _ \ '__|
    | |_) | (_| \__ \__ \  __/ | | | (_| |  __/ |
    | .__/ \__,_|___/___/\___|_| |_|\__, |\___|_|
    | |                              __/ |
    |_|                             |___/
     */

    @Override
    public synchronized int leaveAirport() {
        PassengerInterface p = (ServiceProvider) Thread.currentThread();
        int passengerID = p.getPassengerID();

        numberOfArrivedPassengers++;

        return 0;
    }

    /**
     * Gets number of arrived passengers.
     *
     * @return the number of arrived passengers
     */
    public int getNumberOfArrivedPassengers() {
        return numberOfArrivedPassengers;
    }
}
