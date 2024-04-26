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

    public void setNrOfRooms(int nrOfRooms) {
        this.nrOfRooms = nrOfRooms;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Property Apartment" + " | Address: " + getAddress() + " | Surface: " + getSurface() + " | Rooms: " + nrOfRooms + " | Owner: " + owner;
    }
}
