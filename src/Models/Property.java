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

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    } 
}
