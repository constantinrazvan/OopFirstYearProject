package Models;

public class House extends Property{
    private int nrOfFloors;
    private String neighbourhood;

    public House(String address, int surface, int nrOfFloors, String neighbourhood) {
        super(address, surface);
        this.nrOfFloors = nrOfFloors;
        this.neighbourhood = neighbourhood;
    }

    public int getNrOfFloors() {
        return nrOfFloors;
    }

    public void setNrOfFloors(int nrOfFloors) {
        this.nrOfFloors = nrOfFloors;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    @Override
    public String toString() {
        return "Property: House " + " | Address: " + getAddress() + " | Surface: " + getSurface() +  " | Floors: " + nrOfFloors + " | Neighbourhood: " + neighbourhood;
    }
}
