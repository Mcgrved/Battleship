package battleship.Game;

import java.util.ArrayList;

public class Ship {
    private ArrayList<int[]> shipPoints;
    private boolean alive;
    private final int type;

    public enum ShipType {
        AIRCRAFT_CARRIER(5), BATTLESHIP(4), SUBMARINE(3), CRUISER(3), DESTROYER(2);

        private int size;

        ShipType(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }
    public Ship(ShipType shipType) {
        alive = true;
        shipPoints = new ArrayList<int[]>();
        type = shipType.ordinal();
    }

    public ArrayList<int[]> getShipPoints() {
        return shipPoints;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}
