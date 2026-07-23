
public class Main {
    public static void main(String[] args) {

        //Creating a fixed game board size
        char[][] gameBoard = new char[11][11];

        for(int row = 0; row < gameBoard.length; row++) {
            for(int col = 0; col < gameBoard[row].length; col++) {
                gameBoard[row][col] = 'X';
            }
        }
        for(int row = 0; row < gameBoard.length; row++) {
            gameBoard[row][3] = '|';
            gameBoard[row][7] = '|';
        }
        for(int col = 0; col < gameBoard.length; col++){
            gameBoard[3][col] = '-';
            gameBoard[7][col] = '-';
        }
        gameBoard[3][3] = '+';
        gameBoard[3][7] = '+';
        gameBoard[7][3] = '+';
        gameBoard[7][7] = '+';

        printingGameBoard(gameBoard);

    }

    public static void printingGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {
            for(char col : row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }
}