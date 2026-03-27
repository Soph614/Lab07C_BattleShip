public class Ship {
    private int length;
    private int shipHits;
    private int[][] coordinates;

    public Ship (int shipLength, int[][] shipCoordinates) {
        this.length = shipLength;
        this.coordinates = shipCoordinates;
        this.shipHits = 0;
    }

    public int getShipHits() {
        return shipHits;
    }

    public void setShipHits(int shipHits) {
        this.shipHits = shipHits;
    }
}
