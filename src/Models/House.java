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

    public String getNeighbourhood() {
        return neighbourhood;
    }
}
