package battleship.Game;

import java.util.Arrays;

public class GameBoard {
    private Cell[][] cellArray;

    GameBoard() {
        cellArray = new Cell[10][10];
        generateBoard();
    }

    private void generateBoard() {
        for (int i = 0; i < this.cellArray.length; i++) {
            for (int j = 0; j < this.cellArray.length; j++) {
                this.cellArray[i][j] = Cell.FOG;
            }
        }
    }

    public String shoot(Player player, int x, int y) {
        if (cellArray[x][y] == Cell.FOG || cellArray[x][y] == Cell.MISS) {
            cellArray[x][y] = Cell.MISS;
            return "You missed!";
        } else if (cellArray[x][y] == Cell.SHIP) {
            cellArray[x][y] = Cell.HIT;
            if(!findHitShip(player, x, y)) {
                if (player.getShipArrayList().isEmpty()) {
                    return "You sank the last ship. You won. Congratulations!";
                } else {
                    return "You sank a ship!";
                }
            }
        }
        return "You hit a ship!";
    }

    private boolean findHitShip(Player player, int x, int y) {
        for (Ship ship : player.getShipArrayList()) {
            for (int[] coordinates : ship.getShipPoints()) {
                if (coordinates[0] == x && coordinates[1] == y) {
                    ship.getShipPoints().remove(coordinates);
                    break;
                }
            }
            checkIfAlive(ship);
            if (!ship.isAlive()) {
                player.getShipArrayList().remove(ship);
                return false;
            }
        }
        return true;
    }

    private void checkIfAlive(Ship ship) {
        if (ship.getShipPoints().isEmpty()) {
            ship.setAlive(false);
        }
    }

    public String createShip(Player player, int xFirst, int yFirst,
                           int xSecond, int ySecond, Ship.ShipType shipType) {
        String result = "";
        if (validateLength(xFirst, yFirst, xSecond, ySecond, shipType)) {
            if (xFirst == xSecond && validatePlacement(Math.min(xFirst, xSecond), Math.min(yFirst, ySecond),
                    true, shipType)) {
                Ship ship = new Ship(shipType);
                player.getShipArrayList().add(ship);
                for (int i = 0; i < shipType.getSize(); i++) {
                    ship.getShipPoints().add(new int[]{xFirst, Math.min(yFirst, ySecond) + i});
                    setCell(xFirst, Math.min(yFirst, ySecond) + i, Cell.SHIP);
                }
            } else if (yFirst == ySecond && validatePlacement(Math.min(xFirst, xSecond), Math.min(yFirst, ySecond),
                    false, shipType)) {
                Ship ship = new Ship(shipType);
                player.getShipArrayList().add(ship);
                for (int i = 0; i < shipType.getSize(); i++) {
                    ship.getShipPoints().add(new int[]{(Math.min(xFirst, xSecond)) + i, yFirst});
                    setCell((Math.min(xFirst, xSecond)) + i, yFirst, Cell.SHIP);
                }
            } else {
                result = "Error! Wrong ship location! Try again:";
            }
        } else {
            result = "Error! Wrong length of the " + shipType.name().substring(0,1).toUpperCase() +
                    shipType.name().substring(1).toLowerCase().replace("_"," ") +
                    "! Try again: ";
        }
        return result;
    }

    private boolean validateLength(int xFirst, int yFirst,
                                   int xSecond, int ySecond, Ship.ShipType shipType) {
            return Math.abs(yFirst - ySecond) + 1 == shipType.getSize() ||
                    Math.abs(xFirst - xSecond) + 1 == shipType.getSize();
    }

    private boolean validatePlacement(int xFirst, int yFirst, boolean placementRow, Ship.ShipType shipType) {
        for (int i = 0; i < shipType.getSize(); i++) {
            if (placementRow) {
                if (!checkNeighborCells(xFirst, yFirst + i)) {
                    return false;
                }
            } else {
                if (!checkNeighborCells(xFirst + i, yFirst)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkNeighborCells(int iCord, int jCord) {
        boolean isCellFree = true;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0) && (iCord + i < getCellArray().length && iCord + i >= 0 &&
                        jCord + j < getCellArray().length && jCord + j >= 0)) {
                    isCellFree = cellArray[iCord + i][jCord + j] != Cell.SHIP;
                    if (!isCellFree) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void setCell(int i, int j, Cell cell) {
        this.cellArray[i][j] = cell;
    }

    public Cell[][] getCellArray() {
        return Arrays.copyOf(this.cellArray, this.cellArray.length);
    }
}
