package Models;

public abstract class Property {
    private String address;
    private int surface;

    public Property(String address, int surface) {
        this.address = address;
        this.surface = surface;
    }

    public String getAddress() {
        return address;
    }

    public int getSurface() {
        return surface;
    }
}
