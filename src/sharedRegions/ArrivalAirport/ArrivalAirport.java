package sharedRegions.ArrivalAirport;

public class ArrivalAirport implements IPassengerAR{

    private int numberOfArrivedPassengers;

    public ArrivalAirport() {
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
    public synchronized void leaveAirport() {
        numberOfArrivedPassengers++;
    }

    public int getNumberOfArrivedPassengers() {
        return numberOfArrivedPassengers;
    }

    public void setNumberOfArrivedPassengers(int numberOfArrivedPassengers) {
        this.numberOfArrivedPassengers = numberOfArrivedPassengers;
    }
}
