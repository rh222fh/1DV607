package model;

public class Boat {
    private Type bt;
    private int length;

    public enum Type { sailboat, motorsailer, canoe, other }

    public Boat(Type bt, int length) {
        this.bt = bt;
        this.length = length;
    }

    public Boat() {}

    public Type getType() { return bt; }
    public void setType(Type bt) { this.bt = bt; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }
}
