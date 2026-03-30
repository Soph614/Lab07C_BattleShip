public class Ship {
    private final int shipLength;
    private int shipHits;
    private final int[][] shipCoordinates;

    public Ship(int lengthOfShip, int[][] coordinatesOfShip) {
        this.shipLength = lengthOfShip;
        this.shipCoordinates = coordinatesOfShip;
        this.shipHits = 0;
    }

    public boolean containsCoordinate(int row, int col) {
        for (int i = 0; i < this.shipLength; i++) {
            if (shipCoordinates[i][0] == row && shipCoordinates[i][1] == col) {
                return true;
            }
        }
        return false;
    }

    public int getShipHits() {
        return shipHits;
    }

    public void setShipHits(int shipHits) {
        this.shipHits = shipHits;
    }

    public boolean isSunk() {
        return this.shipHits == this.shipLength;
    }

    public void recordHit() {
        this.shipHits++;
    }
}