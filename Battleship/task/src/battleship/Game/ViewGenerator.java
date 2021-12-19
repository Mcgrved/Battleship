package battleship.Game;

public class ViewGenerator {

    public static void printGameBoard(GameBoard gameBoard) {
        System.out.print("  ");
        for (int i = 0; i < gameBoard.getCellArray().length; i++) {
            System.out.print(i + 1 +
                    (i < gameBoard.getCellArray().length - 1 ? " " : ""));
        }
        System.out.println();
        for (int i = 0; i < gameBoard.getCellArray().length; i++) {
            System.out.print((char) (65 + i) + " ");
            for (int j = 0; j < gameBoard.getCellArray().length; j++) {
                System.out.print(gameBoard.getCellArray()[i][j].getSymbol() +
                        (j < gameBoard.getCellArray().length - 1 ? " " : ""));
            }
            System.out.println();
        }
    }

    public static void printGameBoard(GameBoard gameBoard, boolean fogged) {
        System.out.print("  ");
        for (int i = 0; i < gameBoard.getCellArray().length; i++) {
            System.out.print(i + 1 +
                    (i < gameBoard.getCellArray().length - 1 ? " " : ""));
        }
        System.out.println();
        for (int i = 0; i < gameBoard.getCellArray().length; i++) {
            System.out.print((char) (65 + i) + " ");
            for (int j = 0; j < gameBoard.getCellArray().length; j++) {
                System.out.print((gameBoard.getCellArray()[i][j] == Cell.SHIP ?
                        Cell.FOG.getSymbol() : gameBoard.getCellArray()[i][j].getSymbol()) +
                        (j < gameBoard.getCellArray().length - 1 ? " " : ""));
            }
            System.out.println();
        }
    }

}
