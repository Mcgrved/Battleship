package battleship.Game;

import java.util.Objects;
import java.util.Scanner;

public class GameController {
    private GameBoard gameBoard;
    private Player player1;
    private Player player2;

    public GameController() {
        this.gameBoard = new GameBoard();
        this.player1 = new Player();
        this.player2 = new Player();
        playGame();
    }
    private void playGame() {
        System.out.println("Player 1, place your ships on the game field");
        placementPhase(player1);
        System.out.println("Player 2, place your ships on the game field");
        placementPhase(player2);
        shootingPhase();

    }

    private void placementPhase(Player player) {
        ViewGenerator.printGameBoard(player.getGameBoard());
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        String result;
        while (player.getShipArrayList().size() < 5) {
            result = "";
            System.out.println("Enter the coordinates of the " +
                    Ship.ShipType.values()[i].name().substring(0,1).toUpperCase() +
                    Ship.ShipType.values()[i].name().substring(1).toLowerCase().replace("_"," ") +
                    " (" + Ship.ShipType.values()[i].getSize() + " cells):");
            result = placeShip(scanner, player, Ship.ShipType.values()[i]);
            if (Objects.equals(result, "")) {
                ViewGenerator.printGameBoard(player.getGameBoard());
                i++;
            } else {
                System.out.println(result);
            }
        }
        enterKey();
    }

    private void shootingPhase() {
        boolean player1Turn = true;
        Player currentPlayer = player1;
        Player otherPlayer = player2;
        Scanner scanner = new Scanner(System.in);
        String result;
        System.out.println("Player 1, it's your turn:");
        while (!player1.getShipArrayList().isEmpty() || !player2.getShipArrayList().isEmpty()) {
            ViewGenerator.printGameBoard(otherPlayer.getGameBoard(), true);
            System.out.println("---------------------");
            ViewGenerator.printGameBoard(currentPlayer.getGameBoard());
            result = shoot(scanner,otherPlayer);
            System.out.println(result);
            enterKey();
            player1Turn = !player1Turn;
            currentPlayer = player1Turn ? player1 : player2;
            otherPlayer = player1Turn ? player2 : player1;
        }
    }

    private String shoot(Scanner scanner, Player player) {
        String coordinates;
        int[] converted;
        do {
            coordinates = scanner.next();
            converted = convertCoordinates(coordinates);
        } while (converted.length == 0);
        String result = "";
        result = player.getGameBoard().shoot(player,converted[0], converted[1]);
        return result;
    }

    private String placeShip(Scanner scanner, Player player, Ship.ShipType shipType) {
        String result;
        String firstCoordinate;
        String secondCoordinate;
        int[] firstConverted;
        int[] secondConverted;

        firstCoordinate = scanner.next();
        secondCoordinate = scanner.next();
        firstConverted = convertCoordinates(firstCoordinate);
        secondConverted = convertCoordinates(secondCoordinate);
        result = player.getGameBoard().createShip(player, firstConverted[0], firstConverted[1],
                secondConverted[0], secondConverted[1], shipType);
        return result;
    }


    private int[] convertCoordinates(String coordinates) {
        if (coordinates != null) {
            if (validateCoordinates(coordinates)) {
                int firstCoordinate = coordinates.charAt(0) - 65;
                int secondCoordinate = Integer.parseInt(coordinates.substring(1, coordinates.length())) - 1;
                return new int[] {firstCoordinate, secondCoordinate};
            } else {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        } else {
            System.out.println("Error! String is null!");
        }
        return new int[] {};
    }

    private boolean validateCoordinates(String coordinates) {
        return coordinates.matches("[A-J]([1-9]|10)");
    }

    private void enterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (Exception e) {

        }
    }
}
