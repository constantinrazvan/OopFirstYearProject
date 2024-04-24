package Models;

public class Apartment extends Property {
    private int nrOfRooms;
    private String owner;

    public Apartment(String address, int surface, int nrOfRooms, String owner) {
        super(address, surface);
        this.nrOfRooms = nrOfRooms;
        this.owner = owner;
    }

    public int getNrOfRooms() {
        return nrOfRooms;
    }

    public String getOwner() {
        return owner;
    }

    public String toString() {
        return "Apartment: " + nrOfRooms + " rooms, owned by " + owner;
    }
}
