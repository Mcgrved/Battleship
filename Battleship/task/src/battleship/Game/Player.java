package battleship.Game;

import java.util.ArrayList;

public class Player {
    private ArrayList<Ship> shipArrayList;
    private GameBoard gameBoard;

    Player() {
        shipArrayList = new ArrayList<Ship>();
        gameBoard = new GameBoard();
    }

    public ArrayList<Ship> getShipArrayList() {
        return shipArrayList;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
}
